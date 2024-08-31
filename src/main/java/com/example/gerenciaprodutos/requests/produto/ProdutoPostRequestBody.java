package com.example.gerenciaprodutos.requests.produto;

import com.example.gerenciaprodutos.model.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_validade;

    @NotBlank(message = "Campo IMAGEM Obrigatório")
    private String image;

    private Categoria categoria;
}
