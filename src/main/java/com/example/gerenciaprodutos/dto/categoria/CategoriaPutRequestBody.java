package com.example.gerenciaprodutos.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaPutRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;

    @NotBlank(message = "Campo NOME Obrigatório")
    private String nome;
}
