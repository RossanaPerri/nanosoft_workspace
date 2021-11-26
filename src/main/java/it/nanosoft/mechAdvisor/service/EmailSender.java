package it.nanosoft.mechAdvisor.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;

/**
 * Gestisce invio e_mail
 * 
 * @author RossanaPerri
 *
 */
public class EmailSender implements Loggable {
	private Properties prop = new Properties();
	private String user;
	private String password;
	private String host;
	private String mittente;
	private String port;
	private String destinatario;
	private String oggetto;
	private String allegato;

	/**
	 * Costruttore che richiede i parametri di connessione al server di posta
	 * 
	 * @param user
	 * @param password
	 * @param host
	 * @param mittente
	 * @param port
	 */
	public EmailSender() {
		try {
			newloggerApp.info("Sto caricando il file emailSender.properties");
			prop.load(EmailSender.class.getClassLoader().getResourceAsStream("emailSender.properties"));
			this.user = prop.getProperty("user").toString();
			this.password = prop.getProperty("password").toString();
			this.host = prop.getProperty("host").toString();
			this.mittente = prop.getProperty("mittente").toString();
			this.port = prop.getProperty("port").toString();
			newloggerApp.info("File emailSender.properties caricato correttamente");
		} catch (IOException e) {
			newloggerApp.info(" ---- Impossibile caricare il file emailSender.properties : ", e);
		}
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getAllegato() {
		return allegato;
	}

	public void setAllegato(String allegato) {
		this.allegato = allegato;
	}

	/**
	 * si occupa dell'invio effettivo della mail
	 */
	public void inviaEmail(String destinatario, String oggetto, String allegato) {
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.user", mittente);
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, null);
		session.setDebug(true);

		// Creazione delle BodyParts del messaggio
		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		try {
			// COSTRUZIONE DEL MESSAGGIO
			Multipart multipart = new MimeMultipart();
			MimeMessage msg = new MimeMessage(session);

			// intestazione del messaggio
			msg.setSubject(oggetto);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(mittente));

			// destinatario
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

			// corpo del messaggio
			messageBodyPart1.setText("Saluti da Nanosoft. Report Query in allegato.");
			multipart.addBodyPart(messageBodyPart1);

			// allegato al messaggio
			DataSource source = new FileDataSource(
					System.getProperty("user.home").concat(System.getProperty("file.separator")).concat(allegato));
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(allegato);
			multipart.addBodyPart(messageBodyPart2);

			// inserimento delle parti nel messaggio
			msg.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

			newloggerApp.info("Invio dell'email Terminato con successo.");

		} catch (AddressException ae) {
			newloggerApp.error(" ---- : ", ae);
		} catch (NoSuchProviderException nspe) {
			newloggerApp.error(" ---- : ", nspe);
		} catch (MessagingException me) {
			newloggerApp.error(" ---- : ", me);
		}

	}

	@Override
	public Logger logging() {
		return null;
	}

}
