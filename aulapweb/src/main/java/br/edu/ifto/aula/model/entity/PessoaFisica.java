package br.edu.ifto.aula.model.entity;

import jakarta.persistence.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Scope("session")//objeto será criado na sessão do usuário
@Entity
@Component
public class PessoaFisica extends Pessoa implements Serializable {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
