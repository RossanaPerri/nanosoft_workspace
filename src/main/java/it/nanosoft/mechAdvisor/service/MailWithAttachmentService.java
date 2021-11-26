package it.nanosoft.mechAdvisor.service;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class MailWithAttachmentService {

    private final String username;
    private final String password;
    private final String host;
    private final int port;

    public MailWithAttachmentService(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendMail(Session session) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("covelliemanuel@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("emicovi@gmail.com"));
        message.setSubject("Report Mech Advisor");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("This is message body");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(getFile("attachment.txt"));
        multipart.addBodyPart(attachmentPart);

       
        message.setContent(multipart);
        Transport.send(message);
    }

    private File getFile(String filename) {
        try {
            URI uri = this.getClass()
              .getClassLoader()
              .getResource(filename)
              .toURI();
            return new File(uri);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to find file from resources: " + filename);
        }
    }

}