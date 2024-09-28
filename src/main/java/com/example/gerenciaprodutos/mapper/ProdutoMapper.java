package com.example.gerenciaprodutos.mapper;

import com.example.gerenciaprodutos.model.Produto;
import com.example.gerenciaprodutos.dto.produto.ProdutoDeleteRequestBody;
import com.example.gerenciaprodutos.dto.produto.ProdutoPostRequestBody;
import com.example.gerenciaprodutos.dto.produto.ProdutoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    public abstract Produto toPostProduto(ProdutoPostRequestBody produtoPostRequestBody);
    public abstract Produto toPutProduto(ProdutoPutRequestBody produtoPutRequestBody);
    public abstract Produto toDeleteProduto(ProdutoDeleteRequestBody produtoDeleteRequestBody);
}
