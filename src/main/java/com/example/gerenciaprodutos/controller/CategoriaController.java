package com.example.gerenciaprodutos.controller;

import com.example.gerenciaprodutos.dto.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.dto.categoria.CategoriaPutRequestBody;
import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Categoria> create(@RequestBody @Valid CategoriaPostRequestBody categoriaPostRequestBody) {
        return new ResponseEntity<>(service.create(categoriaPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<Categoria>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CategoriaPutRequestBody categoriaPutRequestBody)  {
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
