package com.fintech.secfin.repository;

import com.fintech.secfin.model.MetaFinanceira;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetaFinanceiraRepository {

    @PersistenceContext
    private EntityManager em;

    public void salvar(MetaFinanceira meta) {
        em.persist(meta);
    }

    public List<MetaFinanceira> listarPorUsuario(Long usuarioId) {
        return em.createQuery("SELECT m FROM MetaFinanceira m WHERE m.usuario.id = :id", MetaFinanceira.class)
                 .setParameter("id", usuarioId)
                 .getResultList();
    }
}
