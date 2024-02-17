package com.alexandre.oliveira.crudclients.services;

import com.alexandre.oliveira.crudclients.dto.ClientResponseDTO;
import com.alexandre.oliveira.crudclients.dto.CreateClientDTO;
import com.alexandre.oliveira.crudclients.entities.Client;
import com.alexandre.oliveira.crudclients.exceptions.ClientNotFoundException;
import com.alexandre.oliveira.crudclients.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        var clients = this.repository.findAll(pageable);
        return clients.map(x -> new ClientResponseDTO(
                x.getId(), x.getName(), x.getCpf(), x.getIncome(), x.getBirthDate(), x.getChildren())
        );
    }
    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        var client = this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return client.copyToClientResponseDTO(client);
    }
    @Transactional
    public ClientResponseDTO create(CreateClientDTO createClientDTO){
        var clientCreated = this.repository.save(createClientDTO.copyToClient(createClientDTO));
        return clientCreated.copyToClientResponseDTO(clientCreated);
    }
    @Transactional
    public ClientResponseDTO update(Long id, CreateClientDTO createClientDTO) {
        var client = this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        client.setName(createClientDTO.getName());
        client.setCpf(createClientDTO.getCpf());
        client.setIncome(createClientDTO.getIncome());
        client.setBirthDate(createClientDTO.getBirthDate());
        client.setChildren(createClientDTO.getChildren());
        var clientUpdated = this.repository.save(client);
        return clientUpdated.copyToClientResponseDTO(clientUpdated);
    }


}
