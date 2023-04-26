package com.mainproject.talentbridge.communication;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailTry2 {
  
  public static void sendEmail() {
    
    String to = "gargshivam0011@gmail.com"; // recipient email address
    String from = "gargshivam0011@gmail.com"; // sender email address
//    String host = "email-smtp.us-east-1.amazonaws.com"; // SES SMTP endpoint
//    String username = "AKIAS6ELPF44QA23PLWR"; // AWS Access Key ID
//    String password = "BC0ZCtG0g%2BDkk3EC5kKjW0ogdGcGLXWb%2F8%2BM5eDutrGt"; // AWS Secret Access Key
////    String password = "BC0ZCtG0g+Dkk3EC5kKjW0ogdGcGLXWb/8+M5eDutrGt"; // AWS Secret Access Key

    
    String username = "AKIAXOVC5JAHTBKCNAOI";
    
    String password = "BDQwYprflcSU/PlyYC8Cz4yvQCXG1iQzN5iQYq4xBoDv";
    
    String host = "email-smtp.eu-west-1.amazonaws.com";
    
    int PORT = 587;
    
    
    // create properties object and set SMTP server properties
    Properties properties = new Properties();
//    properties.setProperty("mail.transport.protocol", "smtp");
//    properties.setProperty("mail.smtp.auth", "true");
//    properties.setProperty("mail.smtp.starttls.enable", "true");
//    properties.setProperty("mail.smtp.host", host);
//    properties.setProperty("mail.smtp.port", "587");
//    properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//    properties.setProperty("mail.debug", "true");
//    properties.setProperty("mail.smtp.auth.mechanisms", "PLAIN");

    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", PORT);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    
    
    // create session object and authenticate with AWS SES
    Authenticator auth = new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };
    Session session = Session.getInstance(properties, auth);
    
    // create message object and set message properties
    MimeMessage message = new MimeMessage(session);
    try {
      System.out.println("trying now");
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("Test Email via AWS SES");
      message.setText("This is a test email sent via AWS SES sandbox account.");

      // send email
      Transport.send(message);
      System.out.println("Email sent successfully.");
      
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
  }
}
