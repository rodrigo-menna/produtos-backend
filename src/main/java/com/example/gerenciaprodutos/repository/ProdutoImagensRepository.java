package com.example.gerenciaprodutos.repository;

import com.example.gerenciaprodutos.model.ProdutoImagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagem, Long> {

    List<ProdutoImagem> findByProdutoId(Long id);

}
