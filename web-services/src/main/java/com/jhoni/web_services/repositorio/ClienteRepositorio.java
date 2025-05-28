package com.jhoni.web_services.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhoni.web_services.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
