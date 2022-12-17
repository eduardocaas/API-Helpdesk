package com.efc.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efc.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
