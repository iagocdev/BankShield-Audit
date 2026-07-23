# BankShield-Audit

O **BankShield-Audit** é um sistema bancário interativo simulado em Java, projetado para o processamento e auditoria de operações financeiras (Saques e Empréstimos) com foco em segurança de regras de negócio, cálculos de precisão e testes automatizados.

---

##  Regras de Negócio & Escudos de Proteção

1. **Validação de Saque:** Impede saques com valores negativos, zerados ou superiores ao saldo atual disponível (`IllegalArgumentException`).
2. **Análise de Crédito para Empréstimo:** Calcula o limite pré-aprovado do cliente (5x a renda mensal) e bloqueia solicitações acima desse teto.
3. **Projeção de Amortização (Tabela Price):** Calcula dinamicamente o valor das parcelas, o Custo Efetivo Total (CET), o total pago em juros e a projeção mensal de vencimentos.

---

## Conceitos Avançados Aplicados

* **Orientação a Objetos Avançada:** Abstração via `interface Operavel` e herança através da classe abstrata `OperacaoBancaria`.
* **Polimorfismo:** Sobrescrita do método `executar()` para comportamentos distintos de cada modalidade bancária.
* **Math & Money:** Uso rigoroso de `BigDecimal` (`pow`, `multiply`, `divide`, `setScale`) para eliminar erros de arredondamento em operações financeiras.
* **Date & Formatting:** Utilização da API `java.time` (`LocalDate`) para projeção de vencimentos futuros e `NumberFormat` para moeda local (R$).
* **Testes Automatizados:** Suíte de testes unitários com JUnit 5 para validação contínua das regras financeiras.
* **Terminal Interativo:** Interface CLI construída com `java.util.Scanner`.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Apache Maven** (Gerenciamento de dependências e build)
* **JUnit 5** (Framework de testes unitários)
* **BigDecimal API** (Precisão cirúrgica para cálculos monetários)
* **Java Time API** (Manipulação nativa de datas)

---

## Estrutura do Projeto

```text
BankShield-Audit/
├── pom.xml                   <-- Gerenciamento do Maven e JUnit 5
├── README.md
└── src/
    ├── main/
    │   └── java/             <-- Código-fonte da aplicação
    │       ├── model/        <-- OperacaoBancaria, Saque, Emprestimo, etc.
    │       └── service/      <-- Main (Terminal interativo)
    └── test/
        └── java/             <-- Suíte de testes unitários
            ├── SaqueTest.java
            └── EmprestimoTest.java
```
Como Executar os Testes Unitários

Para compilar o projeto e rodar todos os testes automatizados via linha de comando:
```text
mvn test
```
Como Executar a Aplicação
    Clone o repositório:
```text
git clone [https://github.com/iagocdev/BankShield-Audit.git](https://github.com/iagocdev/BankShield-Audit.git)
```
Compile e execute a aplicação via Maven:
```text
mvn compile
mvn exec:java -Dexec.mainClass="service.Main"
```