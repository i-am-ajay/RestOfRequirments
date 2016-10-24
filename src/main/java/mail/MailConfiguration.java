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
	public void generateAndSendMessage(String from) throws AddressException, MessagingException{
		Session session = mailConfigure();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ajaychandel.1987@gmail.com, "
				+ "ashok.kumar@gmail.com"));
		
		
	}
}
