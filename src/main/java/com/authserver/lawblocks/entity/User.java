package com.authserver.lawblocks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User {
    private static final int MAX_NICKNAME_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = MAX_NICKNAME_LENGTH)
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "birth", nullable = false)
    private String birth;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Builder
    public User(String userId, String password, String email, String nickname, String realName, String birth, Certification certification) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.realName = realName;
        this.birth = birth;
        this.certification = certification;
    }
}
