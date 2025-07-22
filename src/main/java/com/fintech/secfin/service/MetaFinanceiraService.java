package com.fintech.secfin.service;

import com.fintech.secfin.dto.SimulacaoDTO;
import com.fintech.secfin.model.MetaFinanceira;
import com.fintech.secfin.model.Rendimento;
import com.fintech.secfin.model.Usuario;
import com.fintech.secfin.repository.MetaFinanceiraRepository;
import com.fintech.secfin.repository.RendimentoRepository;
import com.fintech.secfin.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetaFinanceiraService {

    private final MetaFinanceiraRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final RendimentoRepository rendimentoRepo;

    public MetaFinanceiraService(MetaFinanceiraRepository repo,
                                 UsuarioRepository usuarioRepo,
                                 RendimentoRepository rendimentoRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.rendimentoRepo = rendimentoRepo;
    }

    @Transactional
    public String adicionarMeta(Long usuarioId, MetaFinanceira meta) {
        Usuario usuario = usuarioRepo.buscarPorId(usuarioId);
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        meta.setUsuario(usuario);
        repo.salvar(meta);
        return "Meta cadastrada com sucesso!";
    }

    public List<MetaFinanceira> listarPorUsuario(Long usuarioId) {
        return repo.listarPorUsuario(usuarioId);
    }

    public List<SimulacaoDTO> simular(Long usuarioId) {
        List<MetaFinanceira> metas = repo.listarPorUsuario(usuarioId);
        List<Rendimento> rendimentos = rendimentoRepo.listarPorUsuarioId(usuarioId);
        List<SimulacaoDTO> resultados = new ArrayList<>();

        double totalMensal = rendimentos.stream()
                                        .mapToDouble(Rendimento::getValor)
                                        .sum();

        for (MetaFinanceira meta : metas) {
            SimulacaoDTO dto = new SimulacaoDTO();
            dto.setDescricao(meta.getDescricao());
            dto.setValorObjetivo(meta.getValorObjetivo());

            if (totalMensal <= 0) {
                dto.setMeses(-1); // erro de cálculo
            } else {
                dto.setMeses((int) Math.ceil(meta.getValorObjetivo() / totalMensal));
            }

            resultados.add(dto);
        }

        return resultados;
    }
}
