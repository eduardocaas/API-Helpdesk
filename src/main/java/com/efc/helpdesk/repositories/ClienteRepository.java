package com.efc.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efc.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
