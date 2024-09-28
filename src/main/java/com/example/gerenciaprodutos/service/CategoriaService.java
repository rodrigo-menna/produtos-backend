package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.mapper.CategoriaMapper;
import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.repository.CategoriaRepository;
import com.example.gerenciaprodutos.dto.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.dto.categoria.CategoriaPutRequestBody;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria create(CategoriaPostRequestBody requestBody) {
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(requestBody);
        categoria.setDataCriacao(new Date());
        return this.repository.save(categoria);
    }

    public Optional<Categoria> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categgoria não encontrada")));
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public void update(CategoriaPutRequestBody requestBody) throws BadRequestException {
        Optional<Categoria> found = findById(requestBody.getId());
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(requestBody);
        categoria.setDataAtualizacao(new Date());
        categoria.setId(found.get().getId());
        this.repository.save(categoria);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
