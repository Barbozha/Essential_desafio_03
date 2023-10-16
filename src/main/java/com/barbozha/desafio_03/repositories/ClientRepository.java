package com.barbozha.desafio_03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbozha.desafio_03.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
