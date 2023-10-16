package com.barbozha.desafio_03.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbozha.desafio_03.dto.ClientDTO;
import com.barbozha.desafio_03.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
		Page<ClientDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
}