package br.edu.ifto.aula.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Venda implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    LocalDateTime dataEHorario;

    @ManyToOne
    Pessoa pessoa; //PF ou PJ


}
