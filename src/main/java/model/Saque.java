package model;

import java.math.BigDecimal;

public class Saque extends OperacaoBancaria {
    private BigDecimal saldoAtual;


    public Saque (String idOperacao, BigDecimal saldoAtual){
        super(idOperacao , TipoOperacao.SAQUE);
        this.saldoAtual = saldoAtual;
    }
    @Override
    public void executar(BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor precisa ser positivo");
        }
        if (valor.compareTo(saldoAtual) > 0){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldoAtual = saldoAtual.subtract(valor);

        System.out.println("model.Saque realizado com sucesso! Novo saldo  R$: "+saldoAtual);

    }


}
