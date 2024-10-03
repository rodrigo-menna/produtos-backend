package com.example.gerenciaprodutos.controller;

import com.example.gerenciaprodutos.model.ProdutoImagem;
import com.example.gerenciaprodutos.service.ProdutoImagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtoImagem")
public class ProdutoImagensController {

    @Autowired
    private ProdutoImagensService produtoImagensService;

    @GetMapping("/get")
    public List<ProdutoImagem> findAll() {
        return produtoImagensService.findAll();
    }

    @GetMapping("/find/{id}")
    public List<ProdutoImagem> findById(@PathVariable("id") Long id) {
        return produtoImagensService.findByProdutoId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        produtoImagensService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}