package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.mapper.CategoriaMapper;
import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.repository.CategoriaRepository;
import com.example.gerenciaprodutos.requests.categoria.CategoriaDeleteRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria create(CategoriaPostRequestBody requestBody) {
        return this.repository.save(CategoriaMapper.INSTANCE.toCategoria(requestBody));
    }

    public Optional<Categoria> findById(Long id) throws BadRequestException {
        return Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new BadRequestException("Categgoria não encontrada")));
    }

    public List<Categoria> findAll() {
        return this.repository.findAll();
    }

    public void update(CategoriaPutRequestBody requestBody) throws BadRequestException {
        Optional<Categoria> found = findById(requestBody.getId());
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(requestBody);
        categoria.setId(found.get().getId());
        this.repository.save(categoria);
    }


    public void delete(CategoriaDeleteRequestBody categoriaDeleteRequestBody) throws BadRequestException {
        Optional<Categoria> categoria = findById(categoriaDeleteRequestBody.getId());
        this.repository.deleteById(categoriaDeleteRequestBody.getId());
    }
}
