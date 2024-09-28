package com.example.gerenciaprodutos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_categoria")
public class Categoria extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;
}
