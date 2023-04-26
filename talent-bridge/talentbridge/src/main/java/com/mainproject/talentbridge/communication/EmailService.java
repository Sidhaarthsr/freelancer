package com.mainproject.talentbridge.communication;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
  
  static final String SMTP_USERNAME = "AKIAXOVC5JAHTBKCNAOI";
  
  static final String SMTP_PASSWORD = "BDQwYprflcSU/PlyYC8Cz4yvQCXG1iQzN5iQYq4xBoDv";
  
  static final String HOST = "email-smtp.eu-west-1.amazonaws.com";
  
  static final int PORT = 587;
  
  public static Boolean sendMail(String email, String emailSubject, String emailBody) {
    try {
      Properties props = System.getProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.port", PORT);
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.debug", "true");
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
      
      Session session = Session.getDefaultInstance(props);
      
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("msb@mysignaturebook.com"));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
      msg.setSubject(emailSubject);
      msg.setContent(emailBody, "text/html");
      
      Transport transport = session.getTransport();
      
      try {
        System.out.println("Trying to send email");
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Email sent");
        return true;
      } catch (Exception ex) {
        System.out.println("error, " + ex);
        return false;
      } finally {
        transport.close();
      }
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }
}
