package com.fintech.secfin.controller;

import com.fintech.secfin.dto.SimulacaoDTO;
import com.fintech.secfin.model.MetaFinanceira;
import com.fintech.secfin.service.MetaFinanceiraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metas")
@CrossOrigin(origins = "http://localhost:3000")
public class MetaFinanceiraController {

    private final MetaFinanceiraService service;

    public MetaFinanceiraController(MetaFinanceiraService service) {
        this.service = service;
    }

    @PostMapping("/{usuarioId}/nova")
    public String novaMeta(@PathVariable Long usuarioId, @RequestBody MetaFinanceira meta) {
        return service.adicionarMeta(usuarioId, meta);
    }

    @GetMapping("/{usuarioId}/listar")
    public List<MetaFinanceira> listar(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{usuarioId}/simular")
    public List<SimulacaoDTO> simular(@PathVariable Long usuarioId) {
        return service.simular(usuarioId);
    }
}
