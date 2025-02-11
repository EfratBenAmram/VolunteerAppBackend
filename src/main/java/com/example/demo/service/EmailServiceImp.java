package com.example.demo.service;

import com.example.demo.model.OrganizationEmailDetails;
import com.example.demo.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //when the organization enter to the admin
    @Override
    public String sendOrganizationDetails(OrganizationEmailDetails organization) {
        try {
            OrganizationEmailDetails emailDetails = new OrganizationEmailDetails(
                    organization.getName(),
                    organization.getRegion(),
                    organization.getOrgGoals(),
                    organization.getRecommendationPhones(),
                    organization.getRecommendationPdfPaths()
            );

            // יצירת המייל
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(sender);
            helper.setTo("efrat.benamram1@gmail.com");
            helper.setSubject("Organization Details: " + emailDetails.getName());

            // גוף המייל
            StringBuilder body = new StringBuilder();
            body.append("Organization Name: ").append(emailDetails.getName()).append("\n")
                    .append("Region: ").append(emailDetails.getRegion()).append("\n")
                    .append("Goals: ").append(emailDetails.getOrgGoals()).append("\n")
                    .append("Recommendation Phones: ").append(String.join(", ", emailDetails.getRecommendationPhones())).append("\n");

            helper.setText(body.toString());

            // הוספת קבצים מצורפים
            for (String pdfPath : emailDetails.getRecommendationPdfPaths()) {
                File file = new File(pdfPath);
                if (file.exists()) {
                    FileSystemResource resource = new FileSystemResource(file);
                    helper.addAttachment(file.getName(), resource);
                }
            }

            mailSender.send(mimeMessage);
            return "Email sent successfully with organization details.";

        } catch (MessagingException e) {
            return "Error while sending email: " + e.getMessage();
        }
    }

    @Override
    public String sendEmailWithAttachment(EmailDetails emailDetails) {
        try {
            // יצירת הודעת מייל
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // הגדרת פרטי המייל
            helper.setTo(emailDetails.getEmail());
            helper.setSubject(emailDetails.getSubject());

            // יצירת תוכן HTML מעוצב
            String emailContent = """
            <div style="font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
                <h2 style="text-align: center; color: #0056b3;">%s</h2>
                <p style="font-size: 16px; line-height: 1.6;">
                    %s
                </p>
                <div style="text-align: center; margin: 20px 0;">
                    <a href="%s" target="_blank" style="text-decoration: none;">
                        <img src="cid:organizationLogo" alt="Organization Logo" style="width: 200px; height: auto; border-radius: 10px; box-shadow: 0px 4px 6px rgba(0,0,0,0.1);">
                    </a>
                </div>
                <p style="text-align: center; font-size: 14px; color: #777;">
                    <i>Click the logo to visit our site.</i>
                </p>
                <footer style="text-align: center; font-size: 12px; color: #999; margin-top: 20px;">
                    <p>© 2024 volunteer. All Rights Reserved.</p>
                </footer>
            </div>
            """.formatted(emailDetails.getSubject(), emailDetails.getMessageBody(), emailDetails.getLogoLink());

            helper.setText(emailContent, true);

            // הוספת הלוגו כתמונה (מהנתיב שנשלח)
            FileSystemResource logo = new FileSystemResource(new File(emailDetails.getLogoPath()));
            helper.addInline("organizationLogo", logo);

            // שליחת ההודעה
            mailSender.send(message);

            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }

    @Override
    public void scheduleEmailSending(EmailDetails emailDetails) {
        Runnable sendEmailTask = () -> {
            try {
                String result = sendEmailWithAttachment(emailDetails);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        LocalDateTime now = LocalDateTime.now();
        Duration delay = Duration.between(now, emailDetails.getSendTime());

        long delayInSeconds = delay.getSeconds();

        if (delayInSeconds >= 0) {
            scheduler.schedule(sendEmailTask, delayInSeconds, TimeUnit.SECONDS);
        } else {
            System.out.println("Send time is in the past. Sending email immediately.");
            sendEmailTask.run();
        }
    }

}
