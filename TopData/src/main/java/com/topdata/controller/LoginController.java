package com.topdata.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.topdata.model.Usuario;
import com.topdata.security.UserDetailsAdapter;
import com.topdata.service.AuthService;
import com.topdata.service.UsuarioService;

import ch.qos.logback.core.model.Model;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/login/api")
public class LoginController {
	
	 private final AuthService authService;
	 private Usuario usuario;
	 private UsuarioService usuarioService;
	 
	 public LoginController( AuthService authService,UsuarioService usuarioService) {
	     this.authService = authService;
	     this.usuarioService = usuarioService;
	 }

	@PostMapping("/acesso")
	public ResponseEntity<String> login(@RequestBody Map<String, Object> loginData) {
		String username = (String) loginData.get("email");
		String password = (String) loginData.get("senha");
		System.out.println("Senha validada Auth");
		usuario = usuarioService.findByUserEmail(username);
		System.out.println(usuario.getEmail());
		
		if (authService.autenticarUsuario(username, password)) {
			UserDetailsAdapter userDetails = new UserDetailsAdapter(usuario);
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, usuario.getPassword(), userDetails.getAuthorities());
	    	SecurityContextHolder.getContext().setAuthentication(authentication);
	    	System.out.println("Auth "+authentication);
			System.out.println("Senha validada Auth");
			handleRedirect();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
    public String handleRedirect() {
        if (isUserAuthenticated()) {
            // O usuário está autenticado, redireciona para a página "/listagem"
        	System.out.println("Autenticadoh");
            return "/cadastro";
        } else {
            // Se o usuário não estiver autenticado, ou for um usuário anônimo, redireciona para a página de login
            return "redirect:/login";
        }
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

}
