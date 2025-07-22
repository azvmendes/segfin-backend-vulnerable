package com.fintech.secfin.service;

import com.fintech.secfin.model.Rendimento;
import com.fintech.secfin.model.Usuario;
import com.fintech.secfin.repository.RendimentoRepository;
import com.fintech.secfin.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendimentoService {

    private final RendimentoRepository rendimentoRepo;
    private final UsuarioRepository usuarioRepo;

    public RendimentoService(RendimentoRepository rendimentoRepo, UsuarioRepository usuarioRepo) {
        this.rendimentoRepo = rendimentoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public String adicionarRendimento(Long usuarioId, Rendimento rendimento) {
        Usuario usuario = usuarioRepo.buscarPorId(usuarioId);
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        rendimento.setUsuario(usuario);
        rendimentoRepo.salvar(rendimento);
        return "Rendimento salvo com sucesso!";
    }

    public List<Rendimento> listarPorUsuario(Long usuarioId) {
        return rendimentoRepo.listarPorUsuarioId(usuarioId);
    }
}
