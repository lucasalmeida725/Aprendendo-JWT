package com.example.Aprendendo_Jwt.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Aprendendo_Jwt.dto.ProdutoRequest;
import com.example.Aprendendo_Jwt.dto.ProdutoResponse;
import com.example.Aprendendo_Jwt.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ProdutoResponse> Criar(@RequestBody @Valid ProdutoRequest dto) {
		ProdutoResponse response = service.CriarProduto(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> Listar() {
		return ResponseEntity.ok(service.Listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> ProcurarId(@PathVariable Long id) {
		ProdutoResponse response = service.ProcurarId(id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Deletar(@PathVariable Long id) {
		service.Remover(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<ProdutoResponse> ProcurarNome(@RequestParam String nome) {
		ProdutoResponse response = service.ProcurarNome(nome);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequest dto) {
	    ProdutoResponse response = service.atualizar(id, dto);
	    return ResponseEntity.ok(response); 
	}


}