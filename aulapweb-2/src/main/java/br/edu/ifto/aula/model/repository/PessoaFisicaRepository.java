package br.edu.ifto.aula.model.repository;

import br.edu.ifto.aula.model.entity.Pessoa;
import br.edu.ifto.aula.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pessoaFisica){
        em.persist(pessoaFisica);
    }

    public List<PessoaFisica> pessoas(){
        Query query = em.createQuery("select pf from PessoaFisica pf");
        return query.getResultList();
    }

    public PessoaFisica pessoa(Long id){
        return em.find(PessoaFisica.class, id);
    }

    public void remove(Long id){
        PessoaFisica p = em.find(PessoaFisica.class, id);
        em.remove(p);
    }

    public void update(PessoaFisica pessoaFisica){
        em.merge(pessoaFisica);
    }
}
