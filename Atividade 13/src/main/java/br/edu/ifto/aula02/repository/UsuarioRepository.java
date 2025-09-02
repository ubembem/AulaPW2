package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario save(Usuario usuario){
        em.persist(usuario);
        return usuario;
    }

    public Usuario usuario(Long id){
        return em.find(Usuario.class, id);
    }

    public List<Usuario> usuarios(){
        Query query = em.createQuery("from Usuario");
        return query.getResultList();
    }

    public void remove(Long id){
        Usuario usuario = em.find(Usuario.class, id);
        em.remove(usuario);
    }

    public void update(Usuario usuario){
        em.merge(usuario);
    }

    public Usuario buscaPorLogin(String login) {
        try {
            Query query = em.createQuery("from Usuario where login ilike :login");
            query.setParameter("login", login);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null caso nenhum usuário seja encontrado
        }
    }
}