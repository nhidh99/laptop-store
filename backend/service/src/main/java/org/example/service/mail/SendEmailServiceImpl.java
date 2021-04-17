package org.example.service.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.example.model.mail.MailProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SendEmailServiceImpl implements SendEmailService {
    private final JavaMailSender sender;
    private final Configuration config;

    @Autowired
    public SendEmailServiceImpl(JavaMailSender sender, @Qualifier("emailConfigBean") Configuration config) {
        this.sender = sender;
        this.config = config;
    }

    @Override
    public void sendEmail(MailProps mailProps) throws MessagingException, IOException, TemplateException {
        Map<String, String> model = new HashMap<>();
        model.put("name", mailProps.getName());
        model.put("location", "Sri Lanka");
        model.put("signature", "https://techmagister.info");
        model.put("content", mailProps.getContent());
        model.put("token", UUID.randomUUID().toString());
        mailProps.setModel(model);

        log.info("Sending Email to: " + mailProps.getTo());
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/techmagisterLogo.png"));

        Template template = config.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailProps.getModel());

        mimeMessageHelper.setTo(mailProps.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailProps.getSubject());
        mimeMessageHelper.setFrom(mailProps.getFrom());

        sender.send(message);
    }
}
