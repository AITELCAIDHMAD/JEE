package org.glsid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpContactsRestApplication implements CommandLineRunner {

	@Autowired
	private ContactRepository contactRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TpContactsRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String dateString = null;
		Date dateNaissance = null;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		dateString = "10/07/1990";
		dateNaissance = format.parse(dateString);
		Contact c1 = new Contact(null, "Ouadghiri", "hassan", dateNaissance, "hassan@gmail.com", "212 694 054 934");
		
		dateString = "24/11/1993";
		dateNaissance = format.parse(dateString);
		Contact c2 = new Contact(null, "Basim", "sanaa", dateNaissance, "sanaa@gmail.com", "212 634 030 348");
		
		dateString = "04/09/1987";
		dateNaissance = format.parse(dateString);
		Contact c3 = new Contact(null, "Hariri", "Fadil", dateNaissance, "fadil@gmail.com", "212 645 345 391");
		
		contactRepository.save(c1);
		contactRepository.save(c2);
		contactRepository.save(c3);
	}
}
