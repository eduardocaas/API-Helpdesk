package com.efc.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efc.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
