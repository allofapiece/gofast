package com.pinwheel.gofast.service.mail;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Email Sender. Service for sending emails.
 *
 * @version 1.0.0
 */
public interface MailSender {
    /**
     * Sends email.
     *
     * @param to            target email.
     * @param subject       subject of the email.
     * @param templateName  name of template which will be rendered.
     * @param model         map for filling view template.
     * @param multiPartMode multipart mode of {@link org.springframework.mail.javamail.MimeMessageHelper}.
     * @param charset       using charset.
     * @return whether message has been send successfully.
     */
    boolean send(String to,
                 String subject,
                 String templateName,
                 Map<String, Object> model,
                 int multiPartMode,
                 Charset charset);

    /**
     * Sends email.
     *
     * @param to      target email.
     * @param subject subject of the email.
     * @param message mail message.
     * @return whether message has been send successfully.
     */
    boolean send(String to, String subject, String message);

    /**
     * Sets general template name property.
     *
     * @param name general template name.
     */
    void setGeneralTemplateName(String name);

    /**
     * Gets general template name property.
     *
     * @return general template name.
     */
    String getGeneralTemplateName();
}
