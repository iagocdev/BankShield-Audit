package service;

import model.Emprestimo;
import model.Saque;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("      🏦 BANKSHIELD - AUTOATENDIMENTO      ");
        System.out.println("==========================================");

        System.out.println("\nQual operação você deseja realizar?");
        System.out.println("1 - Realizar model.Saque");
        System.out.println("2 - Simular / Solicitar Empréstimo");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        if (opcao == 1) {
            // --- FLUXO DE SAQUE ---
            System.out.print("Digite seu saldo atual (R$): ");
            BigDecimal saldo = new BigDecimal(scanner.nextLine());

            System.out.print("Digite o valor do saque (R$): ");
            BigDecimal valorSaque = new BigDecimal(scanner.nextLine());

            Saque saque = new Saque("OP-SAQUE-01", saldo);

            try {
                saque.executar(valorSaque);
            } catch (Exception e) {
                System.out.println("🚫 OPERAÇÃO CANCELADA: " + e.getMessage());
            }

        } else if (opcao == 2) {
            // --- FLUXO DE EMPRÉSTIMO ---
            System.out.print("Informe sua renda mensal (R$): ");
            BigDecimal renda = new BigDecimal(scanner.nextLine());

            Emprestimo emprestimo = new Emprestimo("OP-EMP-01", renda);

            System.out.println("\n💡 Seu limite pré-aprovado é de: R$ " + emprestimo.getLimiteEmprestimo());
            System.out.print("Digite o valor do empréstimo desejado (R$): ");
            BigDecimal valorEmprestimo = new BigDecimal(scanner.nextLine());

            try {
                // Valida o limite do empréstimo
                emprestimo.executar(valorEmprestimo);

                // Pergunta o número de parcelas
                System.out.print("Em quantas parcelas deseja pagar? ");
                int parcelas = scanner.nextInt();

                // Gera o demonstrativo detalhado
                emprestimo.imprimirDemonstrativoBancario(valorEmprestimo, parcelas);

            } catch (Exception e) {
                System.out.println("🚫 CRÉDITO NEGADO: " + e.getMessage());
            }

        } else {
            System.out.println("⚠️ Opção inválida!");
        }

        System.out.println("\n==========================================");
        System.out.println("     Obrigado por utilizar o BankShield!   ");
        System.out.println("==========================================");

        scanner.close();
    }
}