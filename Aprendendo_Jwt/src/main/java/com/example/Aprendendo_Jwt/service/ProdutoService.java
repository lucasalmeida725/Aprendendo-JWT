package com.example.Aprendendo_Jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Aprendendo_Jwt.dto.ProdutoRequest;
import com.example.Aprendendo_Jwt.dto.ProdutoResponse;
import com.example.Aprendendo_Jwt.model.Produto;
import com.example.Aprendendo_Jwt.repository.ProdutoRepository;



@Service
public class ProdutoService {

	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}

	public ProdutoResponse CriarProduto(ProdutoRequest dto) {
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setEstoque(dto.getEstoque());
		produto.setPreco(dto.getPreco());
		Produto salvar = repository.save(produto);
		return new ProdutoResponse(salvar);
	}

	public List<ProdutoResponse> Listar() {
		return repository.findAll().stream().map(ProdutoResponse::new).collect(Collectors.toList());
	}

	public ProdutoResponse ProcurarId(Long id) {
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto com id" + id + "nao encontrado"));
		return new ProdutoResponse(produto);
	}

	public ProdutoResponse ProcurarNome(String nome) {
		Produto produto = repository.findByNomeContainingIgnoreCase(nome)
				.orElseThrow(() -> new RuntimeException("Produto com nome" + nome + "nao encontrado"));
		return new ProdutoResponse(produto);
	}

	public void Remover(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Produto com" + id + "nao encontrado");
		}
		repository.deleteById(id);
	}

	public ProdutoResponse atualizar(Long id, ProdutoRequest dto) {
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto com id " + id + " não encontrado."));

		produto.setNome(dto.getNome());
		produto.setEstoque(dto.getEstoque());
		produto.setPreco(dto.getPreco());

		Produto produtoAtualizado = repository.save(produto);
		return new ProdutoResponse(produtoAtualizado);
	}

}

