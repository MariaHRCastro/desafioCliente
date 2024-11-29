package com.maria.desafioCliente.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public List<ClientDTO> findAll() {
		List<Client> result = repository.findAll();
		List<ClientDTO> dto = new ArrayList<>();
		for (Client c : result) {
			ClientDTO clientDto = new ClientDTO(c);
			dto.add(clientDto);
		}
		return dto;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		Client client = new Client();
		
		/*
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
		client.setIncome(clientDTO.getIncome());
		*/
		
		copyDtoToEntity(clientDTO, client);
		
		repository.save(client);

		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(ClientDTO clientDTO, Long id) {
		Client clientDB = repository.getReferenceById(id);
		/* clientDB.setBirthDate(clientDTO.getBirthDate());
		clientDB.setChildren(clientDTO.getChildren());
		clientDB.setCpf(clientDTO.getCpf());
		clientDB.setIncome(clientDTO.getIncome());
		clientDB.setName(clientDTO.getName());
		*/
		copyDtoToEntity(clientDTO, clientDB);
		repository.save(clientDB);
		return new ClientDTO(clientDB);
	}

	private void copyDtoToEntity(ClientDTO dto, Client client) {
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setName(dto.getName());
		client.setIncome(dto.getIncome());
		client.setCpf(dto.getCpf());

	}

}
