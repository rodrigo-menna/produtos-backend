package com.example.gerenciaprodutos.mapper;

import com.example.gerenciaprodutos.model.ProdutoImagem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoImagemMapper {

    public static final ProdutoImagemMapper INSTANCE = Mappers.getMapper(ProdutoImagemMapper.class);

    public abstract ProdutoImagem toProdutoImage(ProdutoImagem produtoImagem);
}
