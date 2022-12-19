package com.efc.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efc.helpdesk.domain.Tecnico;
import com.efc.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) { // TecnicoRepository retorna um Optional (findById -> JPA)
		
		Optional<Tecnico> obj = repository.findById(id); // Optional -> pode encontrar ou não no banco
		return obj.orElse(null);
	}
	
}
