package com.alexandre.oliveira.crudclients.Controllers;

import com.alexandre.oliveira.crudclients.dto.ClientResponseDTO;
import com.alexandre.oliveira.crudclients.dto.CreateClientDTO;
import com.alexandre.oliveira.crudclients.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service;
    public ClientController(ClientService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }
    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody CreateClientDTO createClientDTO){
        var clientCreated = this.service.create(createClientDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(clientCreated.getId()).toUri()
        ).body(clientCreated);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @RequestBody CreateClientDTO createClientDTO) {
        return ResponseEntity.ok(this.service.update(id, createClientDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
