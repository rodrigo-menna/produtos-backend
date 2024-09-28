package com.example.gerenciaprodutos.service;

import com.example.gerenciaprodutos.mapper.ProdutoImagemMapper;
import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.model.ProdutoImagem;
import com.example.gerenciaprodutos.repository.ProdutoImagensRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        List<ProdutoImagem> listProdutoImagemEncontrados = produtoImagensRepository.findByProdutoId(id);
        List<ProdutoImagem> listProdutoImagem = new ArrayList<>();
        for (ProdutoImagem imagens : listProdutoImagemEncontrados) {
            try (InputStream in = new FileInputStream(diretorioImagens + imagens.getNome())) {
                ProdutoImagem produtoImagem = ProdutoImagemMapper.INSTANCE.toProdutoImage(imagens);
                produtoImagem.setArquivo(IOUtils.toByteArray(in));
                listProdutoImagem.add(produtoImagem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listProdutoImagem;
    }

    public void create(Produto produto, MultipartFile file) {
        ProdutoImagem produtoImagensNovo = new ProdutoImagem();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(file.getOriginalFilename());
                Path caminho = Paths.get(diretorioImagens + nomeImagem);
                Files.write(caminho, bytes);
                produtoImagensNovo.setNome(nomeImagem);
                produtoImagensNovo.setArquivo(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        produtoImagensNovo.setProduto(produto);
        produtoImagensNovo.setDataCriacao(new Date());
        produto.getImagens().add(produtoImagensNovo);
    }

    public ProdutoImagem update(ProdutoImagem produtoImagens) {
        produtoImagens.setDataAtualizacao(new Date());
        return produtoImagensRepository.saveAndFlush(produtoImagens);
    }

    public void delete(Long id) {
        ProdutoImagem produtoImagens = produtoImagensRepository.findById(id).get();
        produtoImagensRepository.delete(produtoImagens);
    }
}