package br.edu.ifto.aula.model.repository;

import br.edu.ifto.aula.model.entity.Departamento;
import br.edu.ifto.aula.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Departamento departamento){
        em.persist(departamento);
    }

    public List<Departamento> departamentos(){
        Query query = em.createQuery("from Departamento");
        return query.getResultList();
    }

    public Departamento departamento(Long id){
        return em.find(Departamento.class, id);
    }

    public void remove(Long id){
        Departamento d = em.find(Departamento.class, id);
        em.remove(d);
    }

    public void update(Departamento departamento){
        em.merge(departamento);
    }
}
