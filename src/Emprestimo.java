import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Emprestimo extends OperacaoBancaria{
    private BigDecimal rendaMensal;
    private BigDecimal limiteEmprestimo;

    private static final BigDecimal TAXA_JUROS = new BigDecimal(("0.084"));

    public Emprestimo (String idOperacao ,BigDecimal rendaMensal ){
        super(idOperacao, TipoOperacao.EMPRESTIMO);
        if (rendaMensal == null){
            throw new IllegalArgumentException("A renda mensal não pode ser nula");
        }
        this.rendaMensal = rendaMensal;
        this.limiteEmprestimo = rendaMensal.multiply(new BigDecimal("5"));

    }

    //metodo pedir emprestimo
    public void executar(BigDecimal valor){
        if (valor == null){
            throw new IllegalArgumentException("O valor do emprestimo nao pode ser nulo");
        }
        if (valor.compareTo(limiteEmprestimo)>0){
            throw new IllegalArgumentException("O valor excede o limite pré aprovado");
        }
        System.out.println("Emprestimo aprovado!");
    }
    //metodo calcular parcela
    public BigDecimal calcularParcela(BigDecimal valorSolicitado, int quantidadeMeses){
        if (valorSolicitado == null || valorSolicitado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor solicitado deve ser maior que zero.");
        }
        if (quantidadeMeses <= 0) {
            throw new IllegalArgumentException("A quantidade de meses deve ser maior que zero.");
        }

        // 1. Calcula (1 + i)
        BigDecimal umMaisTaxa = BigDecimal.ONE.add(TAXA_JUROS);

        // 2. Calcula (1 + i)^n
        BigDecimal fatorPotencia = umMaisTaxa.pow(quantidadeMeses);

        // 3. Numerador: i * (1 + i)^n
        BigDecimal numerador = TAXA_JUROS.multiply(fatorPotencia);

        // 4. Denominador: (1 + i)^n - 1
        BigDecimal denominador = fatorPotencia.subtract(BigDecimal.ONE);

        // 5. Coeficiente: Numerador / Denominador (com 10 casas decimais para manter a precisão)
        BigDecimal coeficiente = numerador.divide(denominador, 10, RoundingMode.HALF_UP);

        // 6. Parcela Final = Valor Solicitado * Coeficiente (arredondado para 2 casas decimais de moeda)
        BigDecimal valorParcela = valorSolicitado.multiply(coeficiente);

        return valorParcela.setScale(2, RoundingMode.HALF_UP);
    }
    public void imprimirDemonstrativoBancario(BigDecimal valorSolicitado, int quantidadeMeses) {
        // 1. Calcula o valor fixo da parcela (usando o método criado anteriormente)
        BigDecimal valorParcela = calcularParcela(valorSolicitado, quantidadeMeses);

        // 2. Calcula o valor total a ser pago (Parcela * Quantidade de Meses)
        BigDecimal valorTotalPago = valorParcela.multiply(new BigDecimal(quantidadeMeses));

        // 3. Calcula o total de juros cobrados (Total Pago - Valor Solicitado)
        BigDecimal totalJuros = valorTotalPago.subtract(valorSolicitado);

        // Formatação de moeda (R$) e data no padrão brasileiro
        NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Data do primeiro vencimento para 30 dias após hoje
        LocalDate dataVencimento = LocalDate.now().plusMonths(1);

        // --- IMPRESSÃO DO EXTRATO BANCÁRIO ---
        System.out.println("\n==================================================");
        System.out.println("          DEMONSTRATIVO DE EMPRÉSTIMO             ");
        System.out.println("==================================================");
        System.out.printf("Valor Solicitado (Principal): %s\n", fmtMoeda.format(valorSolicitado));
        System.out.printf("Quantidade de Parcelas:     %d meses\n", quantidadeMeses);
        System.out.printf("Taxa de Juros Aplicada:     %.2f%% a.m.\n", TAXA_JUROS.multiply(new BigDecimal("100")));
        System.out.println("--------------------------------------------------");
        System.out.println("PROJEÇÃO DAS PARCELAS:");

        // Loop para listar cada uma das parcelas
        for (int i = 1; i <= quantidadeMeses; i++) {
            System.out.printf("Parcela %02d/%02d | Vencimento: %s | Valor: %s\n",
                    i, quantidadeMeses, dataVencimento.format(fmtData), fmtMoeda.format(valorParcela));

            // Avança o vencimento para o próximo mês
            dataVencimento = dataVencimento.plusMonths(1);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("CUSTO EFETIVO TOTAL (CET):");
        System.out.printf("Total Pago em Juros:        %s\n", fmtMoeda.format(totalJuros));
        System.out.printf("VALOR TOTAL A SER PAGO:     %s\n", fmtMoeda.format(valorTotalPago));
        System.out.println("==================================================");
    }

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }

    public BigDecimal getLimiteEmprestimo() {
        return limiteEmprestimo;
    }
}
