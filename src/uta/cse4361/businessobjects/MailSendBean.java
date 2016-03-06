/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import javax.ejb.Stateless;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Abhishek
 */
@Stateless
public class MailSendBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void sendEmail(String fromEmail, String username, String password, String toEmail,String adName,String tos,String date,String time)
    {
        try{
        Properties props = System.getProperties();
        props.put("mail.smtp.user",fromEmail); //added extra
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true"); //added extra
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.debug","true");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.fallback","false");
        Session mailSession = Session.getDefaultInstance(props,null);
        mailSession.setDebug(true);
        Message mailMessage = new MimeMessage(mailSession);
        mailMessage.setFrom(new InternetAddress(fromEmail));
        mailMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        mailMessage.setContent("Advisor Name:"+adName+"<br/>"+"Type of Advising:"+tos+"<br/>"+"Date of Advising:"+date+"<br/>"+"Time of Advising"+time,"text/html");
       
        mailMessage.setSubject("Email");
        
        Transport transport = mailSession.getTransport("smtps");
        transport.connect("smtp.gmail.com",465,username,password);
        transport.sendMessage(mailMessage,mailMessage.getAllRecipients());
        transport.close();
        }
    catch (Exception ex)
    {
    	Logger.getLogger(MailSendBean.class.getName()).log(Level.SEVERE,null,ex);
        
    }
    }
    public void sendEmail(String fromEmail, String username, String password, String toEmail,String password1)
    {
        try{
        Properties props = System.getProperties();
        props.put("mail.smtp.user",fromEmail); //added extra
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true"); //added extra
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.debug","true");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.fallback","false");
        Session mailSession = Session.getDefaultInstance(props,null);
        mailSession.setDebug(true);
        Message mailMessage = new MimeMessage(mailSession);
        mailMessage.setFrom(new InternetAddress(fromEmail));
        mailMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        mailMessage.setContent("Temporary Password:"+password1+"<br/>","text/html");
       
        mailMessage.setSubject("Email");
        
        Transport transport = mailSession.getTransport("smtps");
        transport.connect("smtp.gmail.com",465,username,password);
        transport.sendMessage(mailMessage,mailMessage.getAllRecipients());
        transport.close();
        }
    catch (Exception ex)
    {
    	Logger.getLogger(MailSendBean.class.getName()).log(Level.SEVERE,null,ex);
        
    }
    }
}
