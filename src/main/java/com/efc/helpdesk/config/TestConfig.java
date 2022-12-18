package com.efc.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.efc.helpdesk.services.DBService;

@Configuration
@Profile("test") // Profile definido em properties
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		
		this.dbService.instanciaDB();
		
	}
	
}
