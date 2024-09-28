package com.example.gerenciaprodutos.dto.produto;

import com.example.gerenciaprodutos.model.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoPutRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private LocalDate data_validade;
    private String image;
    private Categoria categoria;
}
