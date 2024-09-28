package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.dto.produto.ProdutoPostRequestBody;
import com.example.gerenciaprodutos.dto.produto.ProdutoPutRequestBody;
import com.example.gerenciaprodutos.mapper.ProdutoMapper;
import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoImagensService produtoImagensService;
    private final CategoriaService categoriaService;

    public Produto create(ProdutoPostRequestBody requestBody) {
        Produto postProduto = ProdutoMapper.INSTANCE.toPostProduto(requestBody);
        postProduto.setDataCriacao(new Date());
        return this.produtoRepository.save(postProduto);
    }

    public Produto createWithFile(ProdutoPostRequestBody requestBody, MultipartFile file) {
        Produto postProduto = ProdutoMapper.INSTANCE.toPostProduto(requestBody);
        postProduto.setDataCriacao(new Date());
        postProduto.setCategoria(categoriaService.findById(postProduto.getCategoria().getId()).get());
        produtoImagensService.create(postProduto, file);
        return produtoRepository.save(postProduto);
    }

    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(this.produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrada")));
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

    public void updateWithImage(ProdutoPutRequestBody requestBody, MultipartFile file) throws BadRequestException {
        Optional<Produto> found = findById(requestBody.getId());
        Produto produto = ProdutoMapper.INSTANCE.toPutProduto(requestBody);
        produto.setId(found.get().getId());
//        List<ProdutoImageResponseBody> byProdutoId = produtoImagensService.findByProdutoId(requestBody.getId());

        this.produtoRepository.save(produto);
    }

    public void delete(Long id) throws BadRequestException {
        Optional<Produto> produto = findById(id);
        this.produtoRepository.deleteById(produto.get().getId());
    }
}
