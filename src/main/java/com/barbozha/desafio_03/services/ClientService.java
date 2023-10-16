package com.barbozha.desafio_03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barbozha.desafio_03.dto.ClientDTO;
import com.barbozha.desafio_03.entities.Client;
import com.barbozha.desafio_03.repositories.ClientRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> cli = repository.findAll(pageable);
		return cli.map(x -> new ClientDTO(x));
	}
}
