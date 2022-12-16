package com.efc.helpdesk.domain;

import java.time.LocalDate;

import com.efc.helpdesk.domain.enums.Prioridade;
import com.efc.helpdesk.domain.enums.Status;

public class Chamado {

	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento = LocalDate.now();
	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacoes;
	
	private Tecnico tecnico;
	private Cliente cliente;
	
}
