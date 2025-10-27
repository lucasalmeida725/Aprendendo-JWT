package com.example.Aprendendo_Jwt.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProdutoRequest {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Positive(message = "Preço deve ser acima de 0")
    @DecimalMax(value = "100000", message = "Valor deve ser até 100000")
    private Double preco;

    @PositiveOrZero(message = "Estoque não pode ser negativo")
    private int estoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}