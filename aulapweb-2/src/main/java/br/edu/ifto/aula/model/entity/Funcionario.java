package br.edu.ifto.aula.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Funcionario extends PessoaFisica implements Serializable {

    private String matricula;

    @ManyToOne
    private Departamento departamento;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "Nome='" + getNome() + '\'' +
                "Cpf='" + getCpf() + '\'' +
                '}';
    }
}
