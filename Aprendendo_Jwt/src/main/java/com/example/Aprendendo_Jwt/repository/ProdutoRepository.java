package com.example.Aprendendo_Jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Aprendendo_Jwt.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByNomeContainingIgnoreCase(String nome);
}
