package com.brq.importer.batch;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brq.importer.models.User;
import com.brq.importer.services.UserService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class ProcessUsers {
	
	private static final Logger log = LoggerFactory.getLogger(ProcessUsers.class);
	
	@Autowired
	private UserService service;
	
	public void processUsers() {
		File dir = new File("C:\\app\\files");
		log.info("Batch importer users from file started");
		for(File arq: dir.listFiles()) {
			String fileName = arq.getName();
			String fileExtension = FilenameUtils.getExtension(arq.getName());
			if(fileExtension.equalsIgnoreCase("csv")) {
				try {
					Reader reader = Files.newBufferedReader(Paths.get(arq.getAbsolutePath()));
				    CSVReader csvReader = new CSVReaderBuilder(reader)
				                            .withSkipLines(1)//ignore header csv.
				                            .build();
	
				    List<String[]> linhas;
					linhas = csvReader.readAll();
					for (String[] linha : linhas)
						for (String coluna : linha) {
							prepareAndRegisterUser(coluna);
						}
					
					boolean success = arq.delete();
					if(success)
						log.info("File success imported and delete: " + fileName);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				log.error("Invalid format: " + arq.getName());
			}
		}
		log.info("Batch importer users from file finish");
	}

	private void prepareAndRegisterUser(String coluna) throws ParseException {
		String[] item = coluna.split(";");
		Integer companyId = Integer.parseInt(item[0]);
		String email = item[1];
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date birthdate = new Date(fmt.parse(item[2]).getTime());
		if(!StringUtils.isEmpty(email) &&
				companyId != null &&
				birthdate != null) {
			User user = new User(companyId, email, birthdate);
			User newUser = service.saveImport(user);
			if(newUser.getUserId() == null)
				log.info("Registered user: " + 
						newUser.getEmail() + 
						" to company: " 
						+ newUser.getCompanyId());
		} else {
			log.error("UNREGISTER USER: email:" + email + 
					"companyId: " + companyId +
					" birthdate" + birthdate);
		}
	}

}
