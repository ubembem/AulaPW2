package br.edu.ifto.aula02.repository;

import br.edu.ifto.aula02.model.entity.ItemVenda;
import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class VendaRepository{

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
        return query.getResultList();
    }

    public void remove(Long id){
        Venda p = em.find(Venda.class, id);
        em.remove(p);
    }

    public void update(Venda venda){
        em.merge(venda);
    }

    public List<Venda> filtrarVendaPorData(LocalDateTime inicio) {
        String hql = "FROM Venda v WHERE v.dataVenda >= :dataInicial";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("dataInicial", inicio);
        return query.getResultList();
    }

    public List<Venda> filtrarVendaPorClienteEPorData(Long clienteId, LocalDateTime dataInicial) {
        // Criando a consulta base
        StringBuilder hql = new StringBuilder("FROM Venda v WHERE v.cliente.id = :clienteId");

        // Adicionando o filtro de data, se os parâmetros forem fornecidos
        if (dataInicial != null ) {
            hql.append(" AND v.dataVenda >= :dataInicial");
        }

        // Criando a query e configurando os parâmetros
        Query query = em.createQuery(hql.toString(), Venda.class);
        query.setParameter("clienteId", clienteId);

        // Se o filtro de data for informado, definimos os parâmetros de data
        if (dataInicial != null) {
            query.setParameter("dataInicial", dataInicial);
        }

        return query.getResultList();
    }

    public List<Venda> filtrarVendaPorClienteLogado(Long clienteId) {
        // Criando a consulta base
        StringBuilder hql = new StringBuilder("FROM Venda v WHERE v.cliente.id = :clienteId");

        // Criando a query e configurando os parâmetros
        Query query = em.createQuery(hql.toString(), Venda.class);
        query.setParameter("clienteId", clienteId);

        return query.getResultList();
    }

}//class