package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@DiscriminatorValue("J")
public class PessoaJuridica extends Pessoa{
    @NotBlank(message = "A razão social é obrigatório!")
    private String razaoSocial;
    @NotBlank
    @CNPJ(message = "Digite um CNPJ válido!")
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

    public String getNome( ){return razaoSocial;}

    public String getCpf( ){return cnpj;}

    public String getTipo( ){return "J";}
}
