package com.example.gerenciaprodutos.dto.categoria;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaDeleteRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;
}
