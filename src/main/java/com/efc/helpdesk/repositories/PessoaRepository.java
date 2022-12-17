package com.efc.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efc.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
