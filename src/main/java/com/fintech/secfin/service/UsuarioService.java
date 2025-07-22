package com.fintech.secfin.service;

import com.fintech.secfin.model.Usuario;
import com.fintech.secfin.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public String cadastrar(Usuario usuario) {
        if (repo.emailJaExiste(usuario.getEmail())) {
            throw new RuntimeException("E-mail já está em uso!");
        }

        try {
            repo.salvar(usuario);
            return "Cadastro efetuado!";
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar usuário.");
        }
    }

    public Usuario getDashboard(Long id) {
        Usuario usuario = repo.buscarPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return usuario;
    }

    public Usuario login(String email, String senha) {
        if (email == null || senha == null || email.isBlank() || senha.isBlank()) {
            throw new RuntimeException("Email e senha são obrigatórios.");
        }

        Usuario usuario = repo.buscarPorEmailESenha(email, senha); // ⚠️ SQLi
        if (usuario == null) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        return usuario;
    }

}
