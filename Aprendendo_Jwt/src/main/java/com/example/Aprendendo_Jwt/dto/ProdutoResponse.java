package com.example.Aprendendo_Jwt.dto;

import com.example.Aprendendo_Jwt.model.Produto;

public class ProdutoResponse {

	private Long id;
	private String nome;
	private Double preco;
	private int estoque;

	public ProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.estoque = produto.getEstoque();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
