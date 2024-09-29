package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.dto.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.dto.categoria.CategoriaPutRequestBody;
import com.example.gerenciaprodutos.mapper.CategoriaMapper;
import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria create(CategoriaPostRequestBody requestBody) {
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(requestBody);
        categoria.setDataCriacao(new Date());
        return this.repository.save(categoria);
    }

    public Categoria findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categgoria não encontrada"));
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public void update(CategoriaPutRequestBody requestBody) {
        findById(requestBody.getId());
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(requestBody);
        categoria.setDataAtualizacao(new Date());
        this.repository.save(categoria);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
