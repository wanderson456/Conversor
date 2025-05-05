import java.util.Scanner;

public class Principal {

        public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);

            String[][] opcoes = {
                    {"USD", "BRL"},
                    {"EUR", "USD"},
                    {"GBP", "BRL"},
                    {"BRL", "EUR"},
                    {"USD", "JPY"},
                    {"EUR", "CHF"}
            };

            System.out.println("=== Conversor de Moedas ===");
            System.out.println("Escolha a conversão desejada:");
            System.out.println("1 - Dólar (USD) → Real (BRL)");
            System.out.println("2 - Euro (EUR) → Dólar (USD)");
            System.out.println("3 - Libra (GBP) → Real (BRL)");
            System.out.println("4 - Real (BRL) → Euro (EUR)");
            System.out.println("5 - Dólar (USD) → Iene (JPY)");
            System.out.println("6 - Euro (EUR) → Franco Suíço (CHF)");
            System.out.print("Digite o número da opção: ");
            int opcao = entrada.nextInt();

            if (opcao < 1 || opcao > 6) {
                System.out.println("Opção inválida.");
                return;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = entrada.nextDouble();

            String moedaOrigem = opcoes[opcao - 1][0];
            String moedaDestino = opcoes[opcao - 1][1];

            ServicoDeCambio servico = new ServicoDeCambio("7546a460f71243ac25acc562");
            ConversorMoeda conversor = new ConversorMoeda(servico);

            try {
                double convertido = conversor.converter(moedaOrigem, moedaDestino, valor);
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", valor, moedaOrigem, convertido, moedaDestino);
            } catch (Exception e) {
                System.out.println("Erro na conversão: " + e.getMessage());
            }

            entrada.close();
        }
    }

