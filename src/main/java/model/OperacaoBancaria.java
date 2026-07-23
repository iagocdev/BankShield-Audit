package model;

public abstract class OperacaoBancaria implements Operavel {
    private String idOperacao;
    private TipoOperacao tipo;

    public OperacaoBancaria(String idOperacao, TipoOperacao saque){
        if (idOperacao == null){
            throw new IllegalArgumentException("O Id não pode ser nulo");
        }
        this.idOperacao = idOperacao;
        this.tipo = tipo;

    }

    public String getIdOperacao() {
        return idOperacao;
    }
    public TipoOperacao getTipo(){
        return tipo;
    }

}
