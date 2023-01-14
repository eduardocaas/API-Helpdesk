package com.efc.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Fulano", "042.173.380-23" , "fulano@email.com", encoder.encode("abc123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Ciclano", "998.332.660-45" , "ciclano@email.com", encoder.encode("OIhQwI"));
		Tecnico tec3 = new Tecnico(null, "Maria", "621.433.020-10" , "maria@email.com", encoder.encode("a2KDCm"));
		Tecnico tec4 = new Tecnico(null, "Ana", "031.196.700-06" , "ana@email.com", encoder.encode("op4lks"));
		Tecnico tec5 = new Tecnico(null, "Paulo", "548.116.140-03" , "paulo@email.com", encoder.encode("Q9IWPY"));
		Tecnico tec6 = new Tecnico(null, "José", "375.545.400-92" , "jose@email.com", encoder.encode("gNKfGA"));
		Tecnico tec7 = new Tecnico(null, "João", "825.421.540-58" , "joao@email.com", encoder.encode("eXIczL"));
		
		
		
		Cliente cli1 = new Cliente(null, "Beltrano", "298.070.940-97", "beltrano@email.com", encoder.encode("bel123trano"));
		Cliente cli2 = new Cliente(null, "Jorge", "748.230.360-00", "jorge@email.com", encoder.encode("zobtk0"));
		Cliente cli3 = new Cliente(null, "Pedro", "209.380.940-09", "pedro@email.com", encoder.encode("y6wdy5"));
		Cliente cli4 = new Cliente(null, "Luis", "421.108.470-42", "luis@email.com", encoder.encode("vh7d34"));
		Cliente cli5 = new Cliente(null, "Gabriel", "106.509.630-59", "gabr@email.com", encoder.encode("ao19cG"));
		Cliente cli6 = new Cliente(null, "Renan", "448.677.540-64", "renan@email.com", encoder.encode("8z1Ans"));
		Cliente cli7 = new Cliente(null, "Natalia", "132.471.870-65", "nat@email.com", encoder.encode("faSJNs"));
		
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec2, cli3);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 03", "Terceiro chamado", tec3, cli2);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6, tec7));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}
	
}
