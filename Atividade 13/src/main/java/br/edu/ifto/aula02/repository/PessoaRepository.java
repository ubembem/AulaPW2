package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Pessoa pessoa){
        em.persist(pessoa);
    }

    public Pessoa pessoa(Long id){
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> pessoas(){
        Query query = em.createQuery("from Pessoa ");
        return query.getResultList();
    }

    public void remove(Long id){
        Pessoa pessoa = em.find(Pessoa.class, id);
        em.remove(pessoa);
    }

    public void update(Pessoa pessoa){
        em.merge(pessoa);
    }

    public List<Pessoa> buscaPorNome(String nome){
        Query query = em.createQuery("from Pessoa where nome ilike :nome or razaoSocial ilike :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
