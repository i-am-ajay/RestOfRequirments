package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailConfiguration {
	Session session;
	public Session mailConfigure(){
		final Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("src/main/resources/config.prop")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authenticator authenticator = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(prop.getProperty("username"),prop.getProperty("password"));
			};
		};
		session = Session.getInstance(prop, authenticator); 
		return session;
	}
	public void generateAndSendMessage(String from, String recipients) throws AddressException, MessagingException{
		Session session = mailConfigure();
		// ceate message object
		Message message = new MimeMessage(session);
		// set from email address
		message.setFrom(new InternetAddress(from));
		// set addresses of recipients
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
		// message subject
		message.setSubject("Test Message");
		message.setText("Hello this is just a test message.");
		
		// send message
		Transport.send(message);
		System.out.println("Message Sent");
	}
	
	public static void main(String [] args){
		MailConfiguration config = new MailConfiguration();
		String from = "sgdummytesting@gmail.com";
		String recipients = "ajaychandel.1987@gmail.com,ajaytranonline@gmail.com";
		try {
			config.generateAndSendMessage( from, recipients);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
