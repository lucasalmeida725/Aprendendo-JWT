package com.example.Aprendendo_Jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Aprendendo_Jwt.model.Usuario;

public interface UserRespository extends JpaRepository<Usuario,String> {

	Optional<Usuario> findByEmail(String email);
}
