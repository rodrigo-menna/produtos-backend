package com.example.gerenciaprodutos.mapper;

import com.example.gerenciaprodutos.model.Categoria;
import com.example.gerenciaprodutos.requests.categoria.CategoriaDeleteRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPostRequestBody;
import com.example.gerenciaprodutos.requests.categoria.CategoriaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoriaMapper {

    public static final CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    public abstract Categoria toCategoria(CategoriaPostRequestBody categoriaPostRequestBody);
    public abstract Categoria toCategoria(CategoriaPutRequestBody categoriaPutRequestBody);
    public abstract Categoria toCategoria(CategoriaDeleteRequestBody categoriaDeleteRequestBody);
}
