package br.edu.ifto.aula02.model.entity;

public enum FormaPagamentoEnum {
    DINHEIRO("Dinheiro"),
    PIX("Pix"),
    CARTAO("Cartão");

    private final String descricao;

    FormaPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
