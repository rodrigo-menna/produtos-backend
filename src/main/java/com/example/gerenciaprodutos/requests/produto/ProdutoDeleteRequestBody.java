package com.example.gerenciaprodutos.requests.produto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDeleteRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;
}
