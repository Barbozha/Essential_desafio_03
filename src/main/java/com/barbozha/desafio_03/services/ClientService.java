package com.barbozha.desafio_03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.barbozha.desafio_03.dto.ClientDTO;
import com.barbozha.desafio_03.entities.Client;
import com.barbozha.desafio_03.repositories.ClientRepository;
import com.barbozha.desafio_03.services.exceptions.DatabaseException;
import com.barbozha.desafio_03.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


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
	
	@Transactional
	public ClientDTO insert(ClientDTO cli) {
		Client entity = new Client();
		copyDtoToEntity(cli,entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			return new ClientDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("cliente inexistente");
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("cliente inexistente");
		}
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de Integridade Referencial");
		}
	}

	private void copyDtoToEntity(ClientDTO cli, Client entity) {
		entity.setName(cli.getName());
		entity.setCpf(cli.getCpf());
		entity.setIncome(cli.getIncome());
		entity.setBirthDate(cli.getBirthDate());
		entity.setChildren(cli.getChildren());
	}
	
}
