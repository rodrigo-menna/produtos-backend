package com.example.gerenciaprodutos.dto.produto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDeleteRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;
}
