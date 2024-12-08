package com.maria.desafioCliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maria.desafioCliente.dto.ClientDTO;
import com.maria.desafioCliente.entities.Client;
import com.maria.desafioCliente.repositories.ClientRepository;
import com.maria.desafioCliente.services.exceptions.DatabaseException;
import com.maria.desafioCliente.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Recurso Não Encontrado!"));
		return new ClientDTO(client) ;
	}

	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> result = repository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso Não Encontrado!");
		}
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial!");
		}

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
		try {
			Client clientDB = repository.getReferenceById(id);
			copyDtoToEntity(clientDTO, clientDB);
			repository.save(clientDB);
			return new ClientDTO(clientDB);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso Não Encontrado!");
		}

	}

	private void copyDtoToEntity(ClientDTO dto, Client client) {
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setName(dto.getName());
		client.setIncome(dto.getIncome());
		client.setCpf(dto.getCpf());

	}

}
