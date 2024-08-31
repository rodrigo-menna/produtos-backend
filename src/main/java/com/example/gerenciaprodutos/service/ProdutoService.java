package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.mapper.ProdutoMapper;
import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.repository.CategoriaRepository;
import com.example.gerenciaprodutos.repository.ProdutoRepository;
import com.example.gerenciaprodutos.requests.produto.ProdutoDeleteRequestBody;
import com.example.gerenciaprodutos.requests.produto.ProdutoPostRequestBody;
import com.example.gerenciaprodutos.requests.produto.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto create(ProdutoPostRequestBody requestBody) {
        return this.produtoRepository.save(ProdutoMapper.INSTANCE.toPostProduto(requestBody));
    }

    public Optional<Produto> findById(Long id) throws BadRequestException {
        return Optional.ofNullable(this.produtoRepository.findById(id).orElseThrow(() -> new BadRequestException("Produto não encontrada")));
    }

    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    public void update(ProdutoPutRequestBody requestBody) throws BadRequestException {
        Optional<Produto> found = findById(requestBody.getId());
        Produto produto = ProdutoMapper.INSTANCE.toPutProduto(requestBody);
        produto.setId(found.get().getId());
        this.produtoRepository.save(produto);
    }

    public void delete(ProdutoDeleteRequestBody produtoDeleteRequestBody) throws BadRequestException {
        Optional<Produto> produto = findById(produtoDeleteRequestBody.getId());
        this.produtoRepository.deleteById(produto.get().getId());
    }
}
