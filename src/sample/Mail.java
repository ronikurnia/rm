package sample;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class Mail {
    public static void sendMail(String msg){
        String to="roni@danareksa.com";
        String from = "roni@bridanareksasekuritas.co.id";
        String host = "172.24.32.110";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.smtp.port","25");
        Session session = Session.getInstance(properties);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("roni@danareksa.com"));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            message.setSubject("Risk Notification");
            message.setText(msg);
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
  }
