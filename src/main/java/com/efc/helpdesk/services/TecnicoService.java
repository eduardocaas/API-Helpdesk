package com.efc.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efc.helpdesk.domain.Tecnico;
import com.efc.helpdesk.domain.dtos.TecnicoDTO;
import com.efc.helpdesk.repositories.TecnicoRepository;
import com.efc.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) { // TecnicoRepository retorna um Optional (findById -> JPA)
		
		Optional<Tecnico> obj = repository.findById(id); // Optional -> pode encontrar ou não no banco
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		
		objDTO.setId(null); // Assegurar que ID virá nulo, para evitar update em ID já existente
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
		
	}
	
}
