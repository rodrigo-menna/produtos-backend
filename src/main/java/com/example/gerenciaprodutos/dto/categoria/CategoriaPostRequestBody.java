package com.example.gerenciaprodutos.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaPostRequestBody {

    @NotBlank(message = "Campo Nome Obrigatório")
    private String nome;
}
