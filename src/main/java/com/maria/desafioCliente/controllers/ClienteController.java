package com.maria.desafioCliente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maria.desafioCliente.dto.ClientDTO;
import com.maria.desafioCliente.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClienteController {
	
	@Autowired
	private ClientService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id ){
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
}
