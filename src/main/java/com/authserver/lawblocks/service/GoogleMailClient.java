package com.authserver.lawblocks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Profile("local")
public class GoogleMailClient implements MailClient {
    private final MailSender mailSender;
    private final String fromEmail;

    public GoogleMailClient(MailSender mailSender, @Value("${spring.mail.username}") String fromEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
    }

    @Override
    @Async
    public void sendMail(Consumer<MailMessage> mailMessageConsumer) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessageConsumer.accept(mailMessage);
        mailMessage.setFrom(fromEmail);
        mailSender.send(mailMessage);
    }
}
