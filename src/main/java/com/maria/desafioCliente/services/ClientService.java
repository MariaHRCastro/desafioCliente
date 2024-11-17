package com.maria.desafioCliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.desafioCliente.dto.ClientDTO;
import com.maria.desafioCliente.entities.Client;
import com.maria.desafioCliente.repositories.ClientRepository;


@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow();
		ClientDTO clientDTO = new ClientDTO(client);
		return clientDTO;
	}
	
}
