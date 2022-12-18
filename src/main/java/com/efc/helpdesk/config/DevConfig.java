package com.efc.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.efc.helpdesk.services.DBService;

@Configuration
@Profile("dev") // Profile definido em properties
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public boolean instanciaDB() {
		
		if(value.equals("create")) { 
			this.dbService.instanciaDB(); // Só cria uma vez
		}	
		return false;	
	}
	
}
