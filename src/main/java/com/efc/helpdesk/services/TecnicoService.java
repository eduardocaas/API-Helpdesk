package com.efc.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.efc.helpdesk.domain.Pessoa;
import com.efc.helpdesk.domain.Tecnico;
import com.efc.helpdesk.domain.dtos.TecnicoDTO;
import com.efc.helpdesk.repositories.PessoaRepository;
import com.efc.helpdesk.repositories.TecnicoRepository;
import com.efc.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.efc.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) { // TecnicoRepository retorna um Optional (findById -> JPA)
		
		Optional<Tecnico> obj = repository.findById(id); // Optional -> pode encontrar ou não no banco
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		
		objDTO.setId(null); // Assegurar que ID virá nulo, para evitar update em ID já existente
		objDTO.setSenha(encoder.encode(objDTO.getSenha())); // Codificação de senha
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
		
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		
		objDTO.setId(id);
		Tecnico oldObj = findById(id); // Verifica de id existe
		validaPorCpfEEmail(objDTO); 
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
		
	}
	
	public void delete(Integer id) {
		
		Tecnico obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço em aberto e não pode ser deletado!");			
		}
		
		repository.deleteById(id);
	
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {  // getId -> caso id do objeto for igual do objDTO, é update, se não, é exceção
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) { 
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
		
	}	
}
