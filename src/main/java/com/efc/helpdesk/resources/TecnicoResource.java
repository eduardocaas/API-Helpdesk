package com.efc.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.efc.helpdesk.domain.Tecnico;
import com.efc.helpdesk.domain.dtos.TecnicoDTO;
import com.efc.helpdesk.services.TecnicoService;

// Intercepta requisições HTTP e envia para camada de service -> que faz conexão com o banco

@RestController
@RequestMapping(value = "/tecnicos") // Endpoint para requisição HTTP
public class TecnicoResource {

	// localhost:8080/tecnicos/2      <- endpoint 
						//    id
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}") // Recebe uma PathVariable
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){ // ResponseEntity -> representa toda resposta HTTP
		
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj)); // Retorna o objeto no corpo da resposta
	}
	
	@GetMapping  // Chamado se não for passado nenhum paramêtro na URL
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList()); // Converte cada obj Tecnico para TecnicoDTO
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){ // No corpo da requisição deve vir um técnico DTO 
		
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		
		Tecnico obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
