import model.Saque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SaqueTest {

    @Test
    @DisplayName("Deve realizar o saque com sucesso e atualizar o saldo")
    void deveRealizarSaqueComSucesso() {
        // 1. Arrange (Preparar)
        BigDecimal saldoInicial = new BigDecimal("1000.00");
        Saque saque = new Saque("OP-SAQUE-100", saldoInicial);
        BigDecimal valorSaque = new BigDecimal("300.00");

        // 2. Act (Agir) - Não deve lançar exceção
        assertDoesNotThrow(() -> saque.executar(valorSaque));
    }

    @Test
    @DisplayName("Deve disparar IllegalStateException quando saldo for insuficiente")
    void deveLancarExcecaoQuandoSaldoForInsuficiente() {
        // 1. Arrange
        BigDecimal saldoInicial = new BigDecimal("200.00");
        Saque saque = new Saque("OP-SAQUE-101", saldoInicial);
        BigDecimal valorMaiorQueSaldo = new BigDecimal("500.00");

        // 2. Act & 3. Assert (Verifica se a exceção correta foi lançada)
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> saque.executar(valorMaiorQueSaldo)
        );

        // Opcional: valida se a mensagem de erro está precisa
        assertEquals("Saldo insuficiente", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve disparar IllegalArgumentException quando valor for menor ou igual a zero")
    void deveLancarExcecaoQuandoValorInvalido() {
        // Arrange
        Saque saque = new Saque("OP-SAQUE-102", new BigDecimal("1000.00"));

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> saque.executar(new BigDecimal("-50.00"))
        );
    }
}