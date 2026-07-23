# BankShield-Audit

O **BankShield-Audit** é um sistema bancário interativo simulado em Java, projetado para o processamento e auditoria de operações financeiras (Saques e Empréstimos) com foco em segurança de regras de negócio e cálculos de precisão.

---

## Regras de Negócio & Escudos de Proteção

1. **Validação de Saque:** Impede saques com valores negativos ou superiores ao saldo atual disponível (`IllegalStateException`).
2. **Análise de Crédito para Empréstimo:** Calcula o limite pré-aprovado do cliente (5x a renda mensal) e bloqueia solicitações acima desse teto.
3. **Projeção de Amortização (Tabela Price):** Calcula dinamicamente o valor das parcelas, o Custo Efetivo Total (CET), o total pago em juros e a projeção mensal de vencimentos.

---

##  Conceitos Avançados Aplicados

* **Orientação a Objetos Avançada:** Abstração via `interface Operavel` e herança através da classe abstrata `OperacaoBancaria`.
* **Polimorfismo:** Sobrescrita do método `executar()` para comportamentos distintos de cada modalidade bancária.
* **Math & Money:** Uso rigoroso de `BigDecimal` (`pow`, `multiply`, `divide`, `setScale`) para eliminar erros de arredondamento em operações financeiras.
* **Date & Formatting:** Utilização da API `java.time` (`LocalDate`) para projeção de vencimentos futuros e `NumberFormat` para moeda local (R$).
* **Terminal Interativo:** Interface CLI construída com `java.util.Scanner`.

---

## Como Executar

1. Clone este repositório:
```bash
git clone [https://github.com/iagocdev/BankShield-Audit.git]([[https://github.com/seu-usuario/BankShield-Audit.git](https://github.com/iagocdev/BankShield-Audit.git)](https://github.com/iagocdev/BankShield-Audit.git))
```
2. Navegue até o diretório do projeto:
```bash
cd BankShield-Audit
```

3. Compile o projeto:
```bash
javac -d bin src/*.java
```

4. Execute a aplicação:
```bash
java -cp bin Main
```
## Tecnologias Utilizadas

* **Java 17+** (ou superior)
* **BigDecimal API** (Precisão cirúrgica para cálculos monetários)
* **Java Time API** (Manipulação nativa de datas)

---
##  Estrutura do Código

* `Operavel`: Interface que define o contrato básico de execução financeira.
* `OperacaoBancaria`: Classe abstrata que centraliza os dados comuns do cliente.
* `Saque`: Implementação das regras de retirada e validação de saldo.
* `Emprestimo`: Mecanismo de análise de crédito e motor de amortização (Tabela Price).
* `Main`: Gerenciador do menu interativo via terminal (Scanner).
