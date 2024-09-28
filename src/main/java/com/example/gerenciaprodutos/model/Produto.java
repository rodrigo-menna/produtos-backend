package com.example.gerenciaprodutos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_produto")
@EqualsAndHashCode(exclude = {"descricao", "preco", "data_validade"}, callSuper = true)
public class Produto extends BaseEntity{

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "data_validade", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data_validade;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutoImagem> imagens;

    public Produto() {
        imagens = new ArrayList<>();
    }
}
