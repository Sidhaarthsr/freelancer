package com.mainproject.talentbridge.communication;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailTry1 {
  
  
  public static void sendEmail(String subject, String body)
      throws UnsupportedEncodingException, MessagingException {
    
    String FROM = "gargshivam0011@gmail.com";
    
    String FROMNAME = "Shivam Garg";
    
    String TO = "gargshivam0011@gmail.com";
    
    String SMTP_USERNAME = "AKIAS6ELPF44QA23PLWR";
    
    String SMTP_PASSWORD = "BC0ZCtG0g%2BDkk3EC5kKjW0ogdGcGLXWb%2F8%2BM5eDutrGt";
    
    String HOST = "email-smtp.us-east-1.amazonaws.com";
    
    int PORT = 587;
    
    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", PORT);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    // props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    
    Session session = Session.getDefaultInstance(props);
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(FROM, FROMNAME));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
    msg.setSubject(subject);
    msg.setContent(body, "text/html");
    
    Transport transport = session.getTransport();
    // <span style="font-weight: 400;"> </span> <span style="font-weight: 400;"> </span>
    
    try {
      System.out.println("Trying to send");
      transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
      transport.sendMessage(msg, msg.getAllRecipients());
      System.out.println("Email sent!");
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e);
    } finally {
      transport.close();
    }
  }
}
