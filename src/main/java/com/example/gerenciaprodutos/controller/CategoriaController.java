package com.example.gerenciaprodutos.controller;

import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.requests.categoria.CategoriaDeleteRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPutRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Categoria> create(@RequestBody @Valid CategoriaPostRequestBody categoriaPostRequestBody) {
        return new ResponseEntity<>(service.create(categoriaPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(service.findById(id));
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CategoriaPutRequestBody categoriaPutRequestBody) throws BadRequestException {
        service.update(categoriaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody CategoriaDeleteRequestBody categoriaDeleteRequestBody) throws BadRequestException {
        this.service.delete(categoriaDeleteRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
