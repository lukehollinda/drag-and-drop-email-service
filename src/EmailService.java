import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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



public class EmailService {
	
	
	public static void sendFormattedEmail(List<File> files)
	{
		String recipientEmail = "luke.hollinda@gmail.com";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		
		String subjectText = "Automated Email Service: " + dtf.format(now);
		
		String bodyText = new String();
		
		//Include file names in body text
		for(File file : files)
		{
			bodyText += file.getName() + "\n";
		}
		
		sendEmail(recipientEmail, subjectText, bodyText, files);
	}

	public static void sendEmail(String recipientEmail, String subjectText, String bodyText, List<File> files)
	{
       // Username of sending email account
       String from = "myemailservice20@gmail.com";

       // Set host to access Gmail SMTP server
       String host = "smtp.gmail.com";

       // Get system properties
       Properties properties = System.getProperties();
       
       
       // Setup mail server
       properties.put("mail.smtp.host", host);
       properties.put("mail.smtp.port", "465");
       properties.put("mail.smtp.ssl.enable", "true");
       properties.put("mail.smtp.auth", "true");

       // Get the Session object and pass username and password
       Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

           protected PasswordAuthentication getPasswordAuthentication() {

               return new PasswordAuthentication("myemailservice20@gmail.com", "GenericPassword");

           }

       });
       
       // Set to TRUE in the case that SMTP issues are occurring
       session.setDebug(false);

       try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           message.setFrom(new InternetAddress(from));

           message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

           message.setSubject(subjectText);

           //Add message
           BodyPart messageBodyPart = new MimeBodyPart();   
           messageBodyPart.setText(bodyText);
           Multipart multipart = new MimeMultipart();          
           multipart.addBodyPart(messageBodyPart);
           
           //Add attachments
           for(File file :files)
           {
        	   messageBodyPart = new MimeBodyPart();
        	   String filename = file.getPath();
        	   DataSource source = new FileDataSource(filename);
        	   messageBodyPart.setDataHandler(new DataHandler(source));
        	   messageBodyPart.setFileName(filename);
        	   multipart.addBodyPart(messageBodyPart);
           }
           
           message.setContent(multipart);
           
           System.out.println("Sending email...");
           
           // Send message
           Transport.send(message);
           
           System.out.println("Sent email successfully...");
       } catch (MessagingException e) {
           e.printStackTrace();
       }
	}
}
