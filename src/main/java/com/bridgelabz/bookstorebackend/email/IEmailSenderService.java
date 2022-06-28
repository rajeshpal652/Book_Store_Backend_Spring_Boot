package com.bridgelabz.bookstorebackend.email;

public interface IEmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
