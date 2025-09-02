package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.EnderecoEntrega;
import br.edu.ifto.aula02.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EnderecoEntregaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(EnderecoEntrega enderecoEntrega){
        em.persist(enderecoEntrega);
    }

    public EnderecoEntrega enderecoEntrega(Long id){
        return em.find(EnderecoEntrega.class, id);
    }

    public List<EnderecoEntrega> enderecoEntregas(){
        Query query = em.createQuery("from EnderecoEntrega ");
        return query.getResultList();
    }

    public void remove(Long id){
        EnderecoEntrega enderecoEntrega = em.find(EnderecoEntrega.class, id);
        em.remove(enderecoEntrega);
    }

    public void update(EnderecoEntrega enderecoEntrega){
        em.merge(enderecoEntrega);
    }

    public List<EnderecoEntrega> buscaPorNome(String logradouro){
        Query query = em.createQuery("from EnderecoEntrega where logradouro ilike :logradouro");
        query.setParameter("logradouro", "%" + logradouro + "%");
        return query.getResultList();
    }
}
