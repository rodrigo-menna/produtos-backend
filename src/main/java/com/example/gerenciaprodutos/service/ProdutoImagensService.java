package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.model.ProdutoImagem;
import com.example.gerenciaprodutos.repository.ProdutoImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoImagensService {

    @Autowired
    private ProdutoImagensRepository produtoImagensRepository;

    @Value("${spring.image.directory}")
    private String diretorioImagens;

    public List<ProdutoImagem> findAll() {
        return produtoImagensRepository.findAll();
    }

    public List<ProdutoImagem> findByProdutoId(Long id) {
        return produtoImagensRepository.findByProdutoId(id);
    }

    public void create(Produto produto, MultipartFile file) {
        ProdutoImagem produtoImagensNovo = new ProdutoImagem();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(file.getOriginalFilename());
                produtoImagensNovo.setNome(nomeImagem);
                produtoImagensNovo.setImagem(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        produtoImagensNovo.setProduto(produto);
        produtoImagensNovo.setDataCriacao(new Date());
        produto.getImagens().add(produtoImagensNovo);
    }

    public void updateWithFile(Produto produto, MultipartFile file) {
        List<ProdutoImagem> byProdutoId = produtoImagensRepository.findByProdutoId(produto.getId());
        ProdutoImagem produtoImagens = new ProdutoImagem();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(file.getOriginalFilename());
                produtoImagens.setNome(nomeImagem);
                produtoImagens.setImagem(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        produtoImagens.setProduto(produto);
        produtoImagens.setDataCriacao(byProdutoId.get(0).getDataCriacao());
        produtoImagens.setDataAtualizacao(new Date());
        produto.getImagens().add(produtoImagens);
    }

    public void delete(Long id) {
        ProdutoImagem produtoImagens = produtoImagensRepository.findById(id).get();
        produtoImagensRepository.delete(produtoImagens);
    }
}