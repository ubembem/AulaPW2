package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.MathContext;

@Table(name = "itemVenda")
@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantidade;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(double quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal total(){
        MathContext mc = new MathContext(2); // Ajuste a precisão conforme necessário
        BigDecimal quantidadeBigDecimal = new BigDecimal(quantidade, mc);
        return quantidadeBigDecimal.multiply(produto.getValor());
    }
}
