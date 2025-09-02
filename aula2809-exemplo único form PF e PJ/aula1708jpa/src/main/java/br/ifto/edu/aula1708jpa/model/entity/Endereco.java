package br.ifto.edu.aula1708jpa.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Endereco {

    @Id
    @GeneratedValue
    private int id;

    LocalDateTime dateTime = LocalDateTime.now();

    private String logradouro;

    @ManyToMany(mappedBy = "enderecos")
    private List<Pessoa> pessoas;

}
