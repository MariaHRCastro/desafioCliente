package com.maria.desafioCliente.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maria.desafioCliente.dto.ClientDTO;
import com.maria.desafioCliente.services.ClientService;

import jakarta.validation.Valid;

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
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
		Page<ClientDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
		clientDTO = service.insert(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(clientDTO);
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id){
		clientDTO = service.update(clientDTO, id);
		return ResponseEntity.ok().body(clientDTO);
	}
	
}
