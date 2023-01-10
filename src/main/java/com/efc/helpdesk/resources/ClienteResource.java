 package com.efc.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.efc.helpdesk.domain.Cliente;
import com.efc.helpdesk.domain.dtos.ClienteDTO;
import com.efc.helpdesk.services.ClienteService;

// Intercepta requisições HTTP e envia para camada de service -> que faz conexão com o banco

@RestController
@RequestMapping(value = "/clientes") // Endpoint para requisição HTTP
public class ClienteResource {

	// localhost:8080/clientes/2      <- endpoint 
						//    id
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}") // Recebe uma PathVariable
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){ // ResponseEntity -> representa toda resposta HTTP
		
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj)); // Retorna o objeto no corpo da resposta
	}
	
	@GetMapping  // Chamado se não for passado nenhum paramêtro na URL
	public ResponseEntity<List<ClienteDTO>> findAll(){
		
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // Converte cada obj Cliente para ClienteDTO
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){ // No corpo da requisição deve vir um cliente DTO 
		
		Cliente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		
		Cliente obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
