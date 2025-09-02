package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@DiscriminatorValue("F")
public class PessoaFisica extends Pessoa {
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;
    @NotBlank
    @CPF(message = "Digite um CPF válido!")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo(){
        return "F";
    }
}
