package br.edu.ifto.aula.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    private Long id;

    @NotBlank
    @Size(min = 3,max = 20)
    private String nome;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa")
    private List<Venda> vendas;

    @OneToOne
    Endereco endereco;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
