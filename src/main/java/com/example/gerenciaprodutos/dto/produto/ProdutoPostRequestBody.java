package com.example.gerenciaprodutos.dto.produto;

import com.example.gerenciaprodutos.model.Categoria;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoPostRequestBody {

    @NotBlank(message = "Campo NOME Obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "Campo PREÇO Obrigatório")
    private Double preco;

    @FutureOrPresent(message = "Campo Data Inválido")
    private LocalDate data_validade;

    private String image;

    private Categoria categoria;
}
