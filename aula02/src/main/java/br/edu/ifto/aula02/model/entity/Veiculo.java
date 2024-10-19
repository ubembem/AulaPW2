package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.GetMapping;

public class Veiculo {
    //@GeneratedValue por enquanto nao está precisando dessa anotação
    private Long id;
    private String marca;
    private String modelo;
    private double preco;
    private int anoFabricacao;

    public Veiculo() {}
    public Veiculo(String marca, String modelo, double preco, int anoFabricacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
        this.anoFabricacao = anoFabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", anoFabricacao=" + anoFabricacao +
                '}';
    }
}
