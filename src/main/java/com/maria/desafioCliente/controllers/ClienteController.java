package com.maria.desafioCliente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maria.desafioCliente.dto.ClientDTO;
import com.maria.desafioCliente.entities.Client;
import com.maria.desafioCliente.services.ClientService;

import jakarta.annotation.PostConstruct;

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
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<ClientDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
		ClientDTO newClient = service.insert(clientDTO);
		return ResponseEntity.ok().body(newClient);
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO, @PathVariable Long id){
		ClientDTO newClient = service.update(clientDTO, id);
		return ResponseEntity.ok().body(newClient);
	}
	
}
