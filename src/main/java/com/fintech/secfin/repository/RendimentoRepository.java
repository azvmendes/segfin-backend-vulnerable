package com.fintech.secfin.repository;

import com.fintech.secfin.model.Rendimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RendimentoRepository {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Rendimento rendimento) {
        em.persist(rendimento);
    }

    public List<Rendimento> listarPorUsuarioId(Long usuarioId) {
        return em.createQuery("SELECT r FROM Rendimento r WHERE r.usuario.id = :id", Rendimento.class)
                 .setParameter("id", usuarioId)
                 .getResultList();
    }
}
