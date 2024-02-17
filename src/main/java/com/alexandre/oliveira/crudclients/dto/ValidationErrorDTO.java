package com.alexandre.oliveira.crudclients.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{
    List<FieldMessageDTO> erros = new ArrayList<>();

    public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDTO> getErros() {
        return erros;
    }

    public void addErro(FieldMessageDTO erro) {
        erros.add(erro);
    }
}
