package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Size(min = 1)
    private List<ItemVenda> itensVenda = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "Obrigatório informar o cliente que está realizando a compra!")
    private Pessoa cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private EnderecoEntrega enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamentoEnum;

    public Venda() {
    }

    public Venda(Long id, LocalDateTime dataVenda, Pessoa cliente) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
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

    //Vou usar esse método para comparar a data de consulta
    public LocalDate getDataVendaDate() {
        return dataVenda.toLocalDate();
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
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

    public void setItensVenda(ItemVenda itensVenda) {
        this.itensVenda.add(itensVenda);
    }

    public Pessoa getPessoa() {
        return cliente;
    }

    public void setPessoa(Pessoa cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", itensVenda=" + itensVenda +
                ", cliente=" + cliente +
                '}';
    }

    public void setItensVenda(@Size(min = 1) List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public @NotNull(message = "Obrigatório informar o cliente que está realizando a compra!") Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull(message = "Obrigatório informar o cliente que está realizando a compra!") Pessoa cliente) {
        this.cliente = cliente;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

//    public FormaPagamento getFormaPagamento() {
//        return formaPagamento;
//    }
//
//    public void setFormaPagamento(FormaPagamento formaPagamento) {
//        this.formaPagamento = formaPagamento;
//    }


    public FormaPagamentoEnum getFormaPagamentoEnum() {
        return formaPagamentoEnum;
    }

    public void setFormaPagamentoEnum(FormaPagamentoEnum formaPagamentoEnum) {
        this.formaPagamentoEnum = formaPagamentoEnum;
    }
}
