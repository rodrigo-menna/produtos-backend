package com.example.gerenciaprodutos.controller;

import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.requests.categoria.CategoriaDeleteRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPutRequestBody;
import com.example.gerenciaprodutos.requests.produto.ProdutoDeleteRequestBody;
import com.example.gerenciaprodutos.requests.produto.ProdutoPostRequestBody;
import com.example.gerenciaprodutos.requests.produto.ProdutoPutRequestBody;
import com.example.gerenciaprodutos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Produto> create(@RequestBody @Valid ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(service.create(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(service.findById(id));
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid ProdutoPutRequestBody categoriaPutRequestBody) throws BadRequestException {
        service.update(categoriaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody ProdutoDeleteRequestBody produtoDeleteRequestBody) throws BadRequestException {
        this.service.delete(produtoDeleteRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
