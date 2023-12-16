package in.sohail.utills;

import java.io.File;
import java.io.FileInputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	@Autowired
	private JavaMailSender emailSender;

	public void sendMail(String to, String subject, String body, File file, String filName) {
		try {
			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setText(subject, body);
			helper.setSubject(subject);
			helper.setTo(to);
			//helper.addAttachment("Citizen Plans", (InputStreamSource) new FileInputStream(file), "application/pdf");
			helper.addAttachment(filName, file);
			emailSender.send(message);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
