package com.fintech.secfin.repository;

import com.fintech.secfin.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario buscarPorEmailESenha(String email, String senha) {
        try {
            String jpql = "SELECT u FROM Usuario u WHERE u.email = '" + email + "' AND u.senha = '" + senha + "'";
            return em.createQuery(jpql, Usuario.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean emailJaExiste(String email) {
        try {
            em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
              .setParameter("email", email)
              .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
