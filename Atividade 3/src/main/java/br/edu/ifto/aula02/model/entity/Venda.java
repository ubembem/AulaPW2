package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataVenda;
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itensVenda = new ArrayList<>();;

    public Venda() {
    }

    public Venda(Long id, LocalDateTime dataVenda) {
        this.id = id;
        this.dataVenda = dataVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItemVenda itensVenda) {
        this.itensVenda.add(itensVenda);
    }

    public BigDecimal total() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemVenda itemVenda : itensVenda) {
            BigDecimal valorProduto = itemVenda.getProduto().getValor();
            BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(itemVenda.getQuantidade());
            BigDecimal valorItem = valorProduto.multiply(quantidadeBigDecimal);
            valorTotal = valorTotal.add(valorItem);
        }
        return valorTotal;
    }
}
