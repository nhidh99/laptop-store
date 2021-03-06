package org.example.service.mail;

import freemarker.template.TemplateException;
import org.example.model.mail.MailProps;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendEmailService {
    void sendEmail(MailProps mailProps) throws MessagingException, IOException, TemplateException;
}
