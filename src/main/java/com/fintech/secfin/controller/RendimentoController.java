package com.fintech.secfin.controller;

import com.fintech.secfin.model.Rendimento;
import com.fintech.secfin.service.RendimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rendimentos")
@CrossOrigin(origins = "http://localhost:3000")
public class RendimentoController {

    private final RendimentoService service;

    public RendimentoController(RendimentoService service) {
        this.service = service;
    }

    @PostMapping("/{usuarioId}/novo")
    public String novoRendimento(@PathVariable Long usuarioId, @RequestBody Rendimento rendimento) {
        return service.adicionarRendimento(usuarioId, rendimento);
    }

    @GetMapping("/{usuarioId}/listar")
    public List<Rendimento> listar(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}
