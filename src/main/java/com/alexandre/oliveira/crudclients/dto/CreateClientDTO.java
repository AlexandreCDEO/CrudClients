package com.alexandre.oliveira.crudclients.dto;

import com.alexandre.oliveira.crudclients.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class CreateClientDTO {
    @NotBlank(message = "Cannot be empty")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Cannot be a future date")
    private LocalDate birthDate;
    private Integer children;

    public CreateClientDTO() {}

    public CreateClientDTO(String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public Client copyToClient(CreateClientDTO clientDTO) {
        return new Client(
                clientDTO.getName(), clientDTO.getCpf(), clientDTO.getIncome(), clientDTO.getBirthDate(), clientDTO.getChildren()
        );
    }
}
