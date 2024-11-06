package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.ItemVenda;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda){
        em.persist(venda);
    }

    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }

    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        System.out.println("Passei no list do repository" + query.getResultList());
        return query.getResultList();
    }

    public void remove(Long id){
        Venda p = em.find(Venda.class, id);
        System.out.println("Venda: " + p.toString() + " removido");
        em.remove(p);
    }

    public void update(Venda venda){
        em.merge(venda);
    }
}