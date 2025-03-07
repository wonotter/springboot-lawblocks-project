package com.authserver.lawblocks.service;

import com.authserver.lawblocks.entity.VerificationCode;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;

@Component
public class VerificationCodeImpl implements VerificationCodeProvider {
    @Override
    public VerificationCode provide() {
        Random random = ThreadLocalRandom.current();
        return random.ints(0, 10)
                .mapToObj(String::valueOf)
                .limit(VerificationCode.LENGTH)
                .collect(collectingAndThen(joining(), VerificationCode::new));
    }
}
