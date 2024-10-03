package com.example.gerenciaprodutos.dto.produto;

import com.example.gerenciaprodutos.model.Categoria;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoPutRequestBody {

    @NotNull(message = "Campo ID Obrigatório")
    private Long id;
    @NotBlank(message = "Campo NOME Obrigatório")
    private String nome;
    private String descricao;
    @NotNull(message = "Campo PREÇO Obrigatório")
    private Double preco;
    @FutureOrPresent(message = "Campo Data Inválido")
    private LocalDate data_validade;
    private Categoria categoria;
}
