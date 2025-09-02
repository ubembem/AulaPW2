package br.edu.ifto.aula.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Endereco implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    String logradouro;

}
