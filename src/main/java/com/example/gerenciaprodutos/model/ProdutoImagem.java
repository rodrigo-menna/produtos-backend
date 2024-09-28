package com.example.gerenciaprodutos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_produto_imagens")
public class ProdutoImagem extends BaseEntity{

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private Produto produto;

    @Transient
    private byte[] arquivo;
}
