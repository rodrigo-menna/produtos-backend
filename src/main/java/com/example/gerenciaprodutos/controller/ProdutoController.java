package com.example.gerenciaprodutos.controller;

import com.example.gerenciaprodutos.dto.produto.ProdutoPostRequestBody;
import com.example.gerenciaprodutos.dto.produto.ProdutoPutRequestBody;
import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping("/create")
    public ResponseEntity<Produto> create(@RequestBody @Valid ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(service.create(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/create/file")
    public ResponseEntity<Produto> createWithFile(@RequestPart ProdutoPostRequestBody produto, @RequestPart("file") MultipartFile file) {
        return new ResponseEntity<>(service.createWithFile(produto, file), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid ProdutoPutRequestBody categoriaPutRequestBody) throws BadRequestException {
        service.update(categoriaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
