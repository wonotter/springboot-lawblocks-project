package com.authserver.lawblocks.service;

import org.springframework.mail.MailMessage;

import java.util.function.Consumer;

public interface MailClient {
    void sendMail(Consumer<MailMessage> mailMessageConsumer);
}
