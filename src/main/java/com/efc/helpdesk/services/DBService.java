package com.efc.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efc.helpdesk.domain.Chamado;
import com.efc.helpdesk.domain.Cliente;
import com.efc.helpdesk.domain.Tecnico;
import com.efc.helpdesk.domain.enums.Perfil;
import com.efc.helpdesk.domain.enums.Prioridade;
import com.efc.helpdesk.domain.enums.Status;
import com.efc.helpdesk.repositories.ChamadoRepository;
import com.efc.helpdesk.repositories.ClienteRepository;
import com.efc.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired 
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Fulano", "042.173.380-23" , "fulano@email.com", "abc123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Beltrano", "298.070.940-97", "beltrano@email.com", "bel123trano");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
}
