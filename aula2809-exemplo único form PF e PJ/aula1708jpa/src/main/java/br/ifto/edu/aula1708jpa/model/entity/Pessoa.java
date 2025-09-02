package br.ifto.edu.aula1708jpa.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    private String telefone;

    @Column(unique = true)
    private String email;

    @ManyToMany
    private List<Endereco> enderecos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public boolean tipoObjeto(String objeto){
        return this.getClass().getSimpleName().toLowerCase().equals(objeto.toLowerCase());
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }

}
