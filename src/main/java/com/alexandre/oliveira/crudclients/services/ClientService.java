package com.alexandre.oliveira.crudclients.services;

import com.alexandre.oliveira.crudclients.dto.ClientResponseDTO;
import com.alexandre.oliveira.crudclients.entities.Client;
import com.alexandre.oliveira.crudclients.exceptions.ClientNotFoundException;
import com.alexandre.oliveira.crudclients.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        var clients = this.repository.findAll(pageable);
        return clients.map(x -> new ClientResponseDTO(
                x.getId(), x.getName(), x.getCpf(), x.getIncome(), x.getBirthDate(), x.getChildren())
        );
    }

    public ClientResponseDTO findById(Long id) {
        var client = this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return new ClientResponseDTO(
                client.getId(), client.getName(), client.getCpf(), client.getIncome(), client.getBirthDate(), client.getChildren()
        );
    }
}
