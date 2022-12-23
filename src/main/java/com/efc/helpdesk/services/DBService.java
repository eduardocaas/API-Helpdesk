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
		Tecnico tec2 = new Tecnico(null, "Ciclano", "998.332.660-45" , "ciclano@email.com", "OIhQwI");
		Tecnico tec3 = new Tecnico(null, "Maria", "621.433.020-10" , "maria@email.com", "a2KDCm");
		
		
		Cliente cli1 = new Cliente(null, "Beltrano", "298.070.940-97", "beltrano@email.com", "bel123trano");
		Cliente cli2 = new Cliente(null, "Jorge", "748.230.360-00", "jorge@email.com", "zobtk0");
		Cliente cli3 = new Cliente(null, "Pedro", "209.380.940-09", "pedro@email.com", "y6wdy5");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec2, cli3);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 03", "Terceiro chamado", tec3, cli2);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}
	
}