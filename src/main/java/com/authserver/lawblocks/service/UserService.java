package com.authserver.lawblocks.service;

import com.authserver.lawblocks.common.exception.BadRequestException;
import com.authserver.lawblocks.common.exception.ErrorCode;
import com.authserver.lawblocks.dto.*;
import com.authserver.lawblocks.entity.Certification;
import com.authserver.lawblocks.entity.User;
import com.authserver.lawblocks.entity.VerificationCode;
import com.authserver.lawblocks.repository.CertificationRepository;
import com.authserver.lawblocks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CertificationRepository certificationRepository;
    private final VerificationCodeProvider verificationCodeProvider;
    private final MailClient mailClient;

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_TIME_DURATION = 15 * 60 * 1000; // 15분

    @Transactional
    public void signUp(SignUpRequestDto dto) {
        String encodePassword = passwordEncoder.encode(dto.password());

        if(userRepository.findByUserId(dto.userId()).isPresent())
            throw new BadRequestException(ErrorCode.USER_ID_DUPLICATED);

        if (userRepository.findByNickname(dto.nickName()).isPresent())
            throw new BadRequestException(ErrorCode.NICKNAME_DUPLICATED);

        Certification certification = certificationRepository.findFirstByEmailOrderByCreatedDateDesc(dto.email()).orElseThrow(() -> new BadRequestException(ErrorCode.CERTIFICATION_FAIL));

//        log.info("certification: {}", certification.getEmail());
//        log.info("dto: {}", dto.email());
//        log.info("certificationNumber: {}", certification.getVerificationCode());
//        log.info("dto: {}", dto.certificationNumber());
        boolean isMatched = certification.getEmail().equals(dto.email())
                && certification.getVerificationCode().getCode().equals(dto.certificationNumber());
        if (!isMatched) throw new BadRequestException(ErrorCode.CERTIFICATION_MISSMATCHING);

        User user = User.builder()
                .userId(dto.userId())
                .password(encodePassword)
                .email(dto.email())
                .nickname(dto.nickName())
                //.realName(dto.realName())
                //.birth(dto.birth())
                .build();

        userRepository.save(user);
        certificationRepository.deleteByEmail(dto.email());
    }

    @Transactional
    public LoginResponseDto signIn(LoginRequestDto dto) {
        String userId = dto.userId();
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_FOUND));

        String password = dto.password();
        String encodedPassword = user.getPassword();
        boolean isMatched = passwordEncoder.matches(password, encodedPassword);

        if (!isMatched)
            throw new BadRequestException(ErrorCode.PASSWORD_NOT_MATCH);

        // 로그인 성공 시, userId와 nickname을 반환
        return new LoginResponseDto(user.getUserId(), user.getNickname());
    }

    @Transactional
    public void emailCertification(EmailCertificationRequestDto dto) {
        Certification certification = createCode(dto.email(), dto.isFindId());
        sendMail(certification);
        certificationRepository.save(certification);
    }

    private Certification createCode(String email, boolean isFindId) {
        VerificationCode verificationCode = verificationCodeProvider.provide();
        if (!isFindId)
            userRepository.findByEmail(email).ifPresent(user -> { throw new BadRequestException(ErrorCode.EMAIL_DUPLICATED); });
        else
            userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_FOUND));
        return Certification.builder()
                .email(email)
                .verificationCode(verificationCode)
                .build();
    }

    private void sendMail(Certification certification) {
        mailClient.sendMail(mail -> {
            mail.setTo(certification.getEmail());
            mail.setSubject("[LAWBlOCKS] 이메일 인증 코드");
            mail.setText("""
                LAWBLOCKS 이메일 인증 코드입니다.
                코드는 다음과 같습니다.
                %s
                """.formatted(certification.getVerificationCode().getCode()));
        });
    }

    @Transactional
    public String findId(FindIdRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_FOUND));

        Certification certification = certificationRepository.findFirstByEmailOrderByCreatedDateDesc(requestDto.email()).orElseThrow(() -> new BadRequestException(ErrorCode.CERTIFICATION_FAIL));

        boolean isMatched = certification.getVerificationCode().getCode().equals(requestDto.code());
        if (!isMatched) throw new BadRequestException(ErrorCode.CERTIFICATION_MISSMATCHING);

        System.out.println(user.getUserId());
        return user.getUserId();
    }
}
