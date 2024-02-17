package com.alexandre.oliveira.crudclients.Controllers;

import com.alexandre.oliveira.crudclients.dto.ClientResponseDTO;
import com.alexandre.oliveira.crudclients.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
