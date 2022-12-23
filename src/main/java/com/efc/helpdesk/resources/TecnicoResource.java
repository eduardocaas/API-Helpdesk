package com.efc.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
