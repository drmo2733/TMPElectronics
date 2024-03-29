package com.example.tmpelectronics.service;


import com.example.tmpelectronics.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MailService {


    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    @Async
    public void sendHtmlEmail(String to, String subject, User user,
                              String link, String templateName,
                              Locale locale) throws MessagingException {
        final Context ctx = new Context(locale);
        ctx.setVariable("name", user.getName());
        ctx.setVariable("url", link);

        final String htmlContent = templateEngine.process(templateName, ctx);

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject(subject);
        message.setTo(to);
        message.setFrom("testyantext@gmail.com");

        message.setText(htmlContent, true); // true = isHtml

        // Send mail
        this.javaMailSender.send(mimeMessage);
    }

}
