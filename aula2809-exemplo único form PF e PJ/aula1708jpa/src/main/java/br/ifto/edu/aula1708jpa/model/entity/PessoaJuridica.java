package br.ifto.edu.aula1708jpa.model.entity;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class PessoaJuridica extends Pessoa implements Serializable {

    private String razaoSocial;

    private String cnpj;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
