

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroConversao {
    private final String moedaOrigem;
    private final String moedaDestino;
    private final double valorOriginal;
    private final double valorConvertido;
    private final LocalDateTime dataHora;

    public RegistroConversao(String moedaOrigem, String moedaDestino, double valorOriginal, double valorConvertido) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%s -> %s | %.2f %s = %.2f %s | %s",
                moedaOrigem, moedaDestino,
                valorOriginal, moedaOrigem,
                valorConvertido, moedaDestino,
                dataHora.format(formato));
    }
}
