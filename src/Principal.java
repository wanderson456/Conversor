import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        String[][] opcoes = {
                {"USD", "BRL"}, // 1
                {"EUR", "USD"}, // 2
                {"GBP", "BRL"}, // 3
                {"BRL", "EUR"}, // 4
                {"USD", "JPY"}, // 5
                {"EUR", "CHF"}, // 6
                {"CAD", "BRL"}, // 7
                {"BRL", "ARS"}, // 8
                {"USD", "BTC"}, // 9
                {"EUR", "JPY"}  // 10
        };


        HistoricoConversao historico = new HistoricoConversao();
        ServicoDeCambio servico = new ServicoDeCambio("sua api key");
        ConversorMoeda conversor = new ConversorMoeda(servico);

        while (true) {
            System.out.println("=== Conversor de Moedas ===");
            System.out.println("1 - Dólar (USD) → Real (BRL)");
            System.out.println("2 - Euro (EUR) → Dólar (USD)");
            System.out.println("3 - Libra (GBP) → Real (BRL)");
            System.out.println("4 - Real (BRL) → Euro (EUR)");
            System.out.println("5 - Dólar (USD) → Iene (JPY)");
            System.out.println("6 - Euro (EUR) → Franco Suíço (CHF)");
            System.out.println("7 - Dólar Canadense (CAD) → Real (BRL)");
            System.out.println("8 - Real (BRL) → Peso Argentino (ARS)");
            System.out.println("9 - Dólar (USD) → Bitcoin (BTC)");
            System.out.println("10 - Euro (EUR) → Iene (JPY)");
            System.out.println("11 - Ver histórico de conversões");
            System.out.println("0 - Sair");
            System.out.print("Digite o número da opção: ");
            int opcao = entrada.nextInt();

            if (opcao == 0) {
                System.out.println("Encerrando...");
                break;
            }

            if (opcao == 11) {
                historico.exibir();
                continue;
            }

            if (opcao < 1 || opcao > 10) {
                System.out.println("Opção inválida.");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = entrada.nextDouble();

            String moedaOrigem = opcoes[opcao - 1][0];
            String moedaDestino = opcoes[opcao - 1][1];

            try {
                double convertido = conversor.converter(moedaOrigem, moedaDestino, valor);
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", valor, moedaOrigem, convertido, moedaDestino);

                RegistroConversao registro = new RegistroConversao(moedaOrigem, moedaDestino, valor, convertido);
                historico.adicionar(registro);

            } catch (Exception e) {
                System.out.println("Erro na conversão: " + e.getMessage());
            }
        }

        entrada.close();
    }
}
