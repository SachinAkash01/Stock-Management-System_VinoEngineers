 package Code;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

public class JavaEmailSender {

    private String emailAddressTo = new String();
    private String msgSubject;
    private String msgText = new String(); 

    final String USER_NAME;  //User name of the Google(gmail) account
    final String PASSSWORD = "password";  //Password of the Google(gmail) account
    final String FROM_ADDRESS = "vinoengineerslk@gmail.co";  //From addresss

    public JavaEmailSender() {
        this.msgSubject = new String();
        this.USER_NAME = "vinoengineerslk@gmail.com";
    }

    public static void main(String[] args) {
        JavaEmailSender email = new JavaEmailSender();
        //Sending test email
        email.createAndSendEmail("vinoengineerslk@gmail.com", "Test email subject",
                "Congratulations !!! \nThis is test email sent by java class.");
    }

    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage(emailAddressTo, msgSubject);
    }

    private void sendEmailMessage(String email, String message1) {

        //Create email sending properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.user", "vinoengineerslk@gmail.com");
        props.put("mail.smtp.password", "password");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vinoengineerslk@gmail.com", "password");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vinoengineerslk@gmail.com")); //Set from address of the email
            message.setContent(msgText, "text/html"); //set content type of the email

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); //Set email recipient

            message.setSubject(message1); //Set email message subject
            Transport.send(message); //Send email message

            System.out.println("sent email successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }

}
