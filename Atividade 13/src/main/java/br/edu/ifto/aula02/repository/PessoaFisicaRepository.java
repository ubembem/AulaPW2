package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.PessoaFisica;
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

    public PessoaFisica pessoaFisica(Long id){
        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoasFisica(){
        Query query = em.createQuery("from PessoaFisica ");
        return query.getResultList();
    }

    public void remove(Long id){
        PessoaFisica pf = em.find(PessoaFisica.class, id);
        em.remove(pf);
    }

    public void update(PessoaFisica pessoaFisica){
        em.merge(pessoaFisica);
    }

    public List<PessoaFisica> buscaPorNome(String nome){
        Query query = em.createQuery("from PessoaFisica where nome ilike :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
