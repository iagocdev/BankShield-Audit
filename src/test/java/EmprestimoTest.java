import model.Emprestimo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    @Test
    @DisplayName("Deve realizar a operação de empréstimo com sucesso quando o valor estiver dentro do limite")
    void deveRealizarEmprestimoComSucesso() {
        // Arrange
        BigDecimal rendaMensal = new BigDecimal("3000.00");
        BigDecimal valorSolicitado = new BigDecimal("5000.00");
        Emprestimo emprestimo = new Emprestimo("OP-EMP-100", rendaMensal);

        // Act & Assert
        assertDoesNotThrow(() -> emprestimo.executar(valorSolicitado));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o empréstimo solicitado ultrapassar 5x a renda mensal")
    void deveLancarExcecaoQuandoExcederLimite() {
        // Arrange
        BigDecimal rendaMensal = new BigDecimal("2000.00"); // Limite = 10.000,00
        BigDecimal valorAcimaDoLimite = new BigDecimal("12000.00");
        Emprestimo emprestimo = new Emprestimo("OP-EMP-101", rendaMensal);

        // Act & Assert
        assertThrows(
                Exception.class,
                () -> emprestimo.executar(valorAcimaDoLimite)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar solicitar valor muito acima da renda mensal")
    void deveLancarExcecaoParaValoresDiscrepantes() {
        // Arrange
        BigDecimal rendaMensal = new BigDecimal("1000.00"); // Limite = 5.000,00
        BigDecimal valorExtremo = new BigDecimal("50000.00");
        Emprestimo emprestimo = new Emprestimo("OP-EMP-102", rendaMensal);

        // Act & Assert
        assertThrows(
                Exception.class,
                () -> emprestimo.executar(valorExtremo)
        );
    }
}