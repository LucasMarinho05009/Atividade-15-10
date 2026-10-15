# NexusCloud Manager — Java

Projeto simples em Java para demonstrar encapsulamento, `equals()`/`hashCode()`, Collections, exceções personalizadas e Streams API.

## Arquivos
- `Cliente.java`: dados do cliente; igualdade pelo CPF.
- `Servidor.java`: servidor, RAM, estado e containers; igualdade pelo IP.
- `Container.java`: aplicação alocada; igualdade pelo ID.
- `ServidorOfflineException.java` e `CapacidadeExcedidaException.java`: exceções de alocação.
- `CloudManager.java`: cadastro, alocação, consultas e cálculo de fatura.
- `MainCloud.java`: exemplo de execução.

## Como executar
É necessário ter o JDK instalado. Abra um terminal na pasta `src` e execute:

```bash
javac *.java
java MainCloud
```

## Observação
Os nomes de métodos e a regra de cobrança foram implementados com base nas informações disponíveis sobre a atividade. Confira o enunciado original para garantir que assinaturas, regras de cálculo e mensagens correspondam exatamente ao que foi solicitado.
