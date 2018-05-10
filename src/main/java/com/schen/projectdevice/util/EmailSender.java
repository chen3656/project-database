package com.schen.projectdevice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.schen.projectdevice.model.UserInfo;

public class EmailSender {

	private String fileName;

	private String filePath;

	private String senderName;

	private String senderEmail;

	private UserInfo recipient;

	private String emailFilePath;
	
	private String body;

	private String subject;

	private final String HOST;
	private final int PORT;
	private final String USERNAME;
	private final String PASSWORD;

	public EmailSender() {
		this.senderName = "Chipone Database";
		this.senderEmail = "do-not-replay@iml-inc.com";
		this.HOST = "smtp-mail.outlook.com";
		this.PORT = 587;
		this.USERNAME = "schen@chiponeusa.com";
		this.PASSWORD = "Hd3280ku";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public UserInfo getRecipient() {
		return recipient;
	}

	public void setRecipient(UserInfo recipient) {
		this.recipient = recipient;
	}

	public String getEmailFilePath() {
		return emailFilePath;
	}

	public void setEmailFilePath(String emailFilePath) {
		this.emailFilePath = emailFilePath;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void generateEmail() throws MessagingException, IOException {
		// Environment
		Session session = Session.getDefaultInstance(new Properties());

		// Mail
		MimeMessage msg = new MimeMessage(session);

		// Set Subject
		msg.setSubject(subject);

		// Sender
		msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText(senderName) + "\"<" + senderEmail + ">"));
		// Recipient
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient.getEmail()));

		// Entire Email MINE
		MimeMultipart msgMultipart = new MimeMultipart("mixed");

		// Set Email MINE
		msg.setContent(msgMultipart);

		// Attachment
		MimeBodyPart attachment = new MimeBodyPart();

		// Content
		MimeBodyPart content = new MimeBodyPart();

		// Add Content and Attachement to MIME message
		msgMultipart.addBodyPart(attachment);
		msgMultipart.addBodyPart(content);

		// Add the file to the Attachment
		DataSource ds = new FileDataSource(new File(filePath));
		DataHandler dh = new DataHandler(ds);
		attachment.setDataHandler(dh);
		attachment.setFileName(MimeUtility.encodeText(fileName));

		// Content HTML
		MimeMultipart bodyMultipart = new MimeMultipart("related");
		content.setContent(bodyMultipart);
		MimeBodyPart htmlPart = new MimeBodyPart();
		bodyMultipart.addBodyPart(htmlPart);
		htmlPart.setContent(
				"<p>" + body + "</p>" + "<p>Chipone Database</p>",
				"text/html;charset=utf-8");
		msg.saveChanges();
		OutputStream os = new FileOutputStream(emailFilePath + File.separator + "email.eml");
		msg.writeTo(os);
		os.close();
	}

	public void sendEmail() throws FileNotFoundException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", String.valueOf(PORT));
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", HOST);
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// Set Account Information Send The Email Using Transport
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		Message message = new MimeMessage(session,
				new FileInputStream(new File(emailFilePath + File.separator + "email.eml")));
		Transport.send(message, InternetAddress.parse(recipient.getEmail()));
	}

	public void sendEmail2() throws FileNotFoundException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "ftp.iml-inc.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props);
		Message message = new MimeMessage(session,
				new FileInputStream(new File(emailFilePath + File.separator + "email.eml")));
		Transport.send(message, InternetAddress.parse(recipient.getEmail()));
	}
}
