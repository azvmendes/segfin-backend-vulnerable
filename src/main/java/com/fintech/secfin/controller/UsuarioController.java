package com.fintech.secfin.controller;

import com.fintech.secfin.model.Usuario;
import com.fintech.secfin.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        try {
            service.cadastrar(usuario);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao registrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            Usuario logado = service.login(usuario.getEmail(), usuario.getSenha());
            return ResponseEntity.ok(logado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<Usuario> dashboard(@PathVariable Long id) {
        Usuario usuario = service.getDashboard(id);
        return ResponseEntity.ok(usuario);
    }
}
