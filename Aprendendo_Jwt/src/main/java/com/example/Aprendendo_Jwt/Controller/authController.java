package com.example.Aprendendo_Jwt.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Aprendendo_Jwt.dto.LoginRequestDTO;
import com.example.Aprendendo_Jwt.dto.RegisterRequestDTO;
import com.example.Aprendendo_Jwt.dto.ResponseDTO;
import com.example.Aprendendo_Jwt.infra.security.TokenService;
import com.example.Aprendendo_Jwt.model.Usuario;
import com.example.Aprendendo_Jwt.repository.UserRespository;

@RestController
@RequestMapping("/auth")
public class authController {

	private final UserRespository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;

	public authController(UserRespository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
	}
	  @PostMapping("/login")
	    public ResponseEntity login(@RequestBody LoginRequestDTO body){
	        Usuario user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
	        if(passwordEncoder.matches(body.password(), user.getPassword())) {
	            String token = this.tokenService.generateToken(user);
	            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
	        }
	        return ResponseEntity.badRequest().build();
	    }


	    @PostMapping("/register")
	    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
	        Optional<Usuario> user = this.repository.findByEmail(body.email());

	        if(user.isEmpty()) {
	            Usuario newUser = new Usuario();
	            newUser.setPassword(passwordEncoder.encode(body.passoword()));
	            newUser.setEmail(body.email());
	            newUser.setName(body.name());
	            this.repository.save(newUser);

	            String token = this.tokenService.generateToken(newUser);
	            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
	        }
	        return ResponseEntity.badRequest().build();
	    }

}
