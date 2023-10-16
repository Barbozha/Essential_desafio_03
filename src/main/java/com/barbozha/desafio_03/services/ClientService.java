package com.barbozha.desafio_03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbozha.desafio_03.dto.ClientDTO;
import com.barbozha.desafio_03.entities.Client;
import com.barbozha.desafio_03.repositories.ClientRepository;
import com.barbozha.desafio_03.services.exceptions.ResourceNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	//Buscando todos os clientes por página
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> cli = repository.findAll(pageable);
		return cli.map(x -> new ClientDTO(x));
	}
	
	//Busca por id retorna cliente existente
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client cli = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("cliente inexistente"));
		return new ClientDTO(cli);
	}
	
}
