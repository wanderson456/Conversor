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

        HistoricoConversao historico = new HistoricoConversao();
        ServicoDeCambio servico = new ServicoDeCambio("7546a460f71243ac25acc562");
        ConversorMoeda conversor = new ConversorMoeda(servico);

        while (true) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - Dólar (USD) → Real (BRL)");
            System.out.println("2 - Euro (EUR) → Dólar (USD)");
            System.out.println("3 - Libra (GBP) → Real (BRL)");
            System.out.println("4 - Real (BRL) → Euro (EUR)");
            System.out.println("5 - Dólar (USD) → Iene (JPY)");
            System.out.println("6 - Euro (EUR) → Franco Suíço (CHF)");
            System.out.println("7 - Ver histórico de conversões");
            System.out.println("0 - Sair");
            System.out.print("Digite o número da opção: ");
            int opcao = entrada.nextInt();

            if (opcao == 0) {
                System.out.println("Encerrando...");
                break;
            }

            if (opcao == 7) {
                historico.exibir();
                continue;
            }

            if (opcao < 1 || opcao > 6) {
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
