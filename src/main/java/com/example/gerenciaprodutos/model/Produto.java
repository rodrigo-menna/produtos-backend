package com.example.gerenciaprodutos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_PRODUTO")
@EqualsAndHashCode(exclude = {"descricao", "preco", "data_validade", "image"})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = true)
    private String descricao;

    @Column(name = "PREÇO", nullable = false)
    private Double preco;

    @Column(name = "DATA_VALIDADE", nullable = false)
    private LocalDate data_validade;

    @Column(name = "IMAGE_PATH", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_ID", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

}
