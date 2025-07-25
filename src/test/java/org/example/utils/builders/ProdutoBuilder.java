package org.example.utils.builders;

import org.example.model.Produto;

public class ProdutoBuilder {
    private String nome = "Produto Genérico";
    private double preco = 10.0;
    private int estoque = 100;


    public ProdutoBuilder comNome(String nome){
        this.nome = nome;
        return this;

    }

    public ProdutoBuilder comPreco( double preco){
        this.preco = preco;
        return this;

    }

    public ProdutoBuilder semEstoque(){
        this.estoque = estoque;
        return this;

    }

    public ProdutoBuilder comEstoque(int estoque){
        this.estoque = estoque;
        return this;

    }

    public Produto build() {
         return new Produto(nome, preco, estoque);

    }
}
