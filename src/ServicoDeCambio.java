import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicoDeCambio {
    private final String chaveApi;

    public ServicoDeCambio(String chaveApi) {
        this.chaveApi = chaveApi;
    }

    public double obterTaxaDeCambio(String moedaOrigem, String moedaDestino) {
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/"
                + chaveApi + "/pair/" + moedaOrigem + "/" + moedaDestino);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar taxa de câmbio: " + e.getMessage());
        }

        String json = response.body();


        RespostaCambio respostaCambio = new Gson().fromJson(json, RespostaCambio.class);

        if (!"success".equals(respostaCambio.result)) {
            throw new RuntimeException("Falha ao obter taxa de câmbio da API.");
        }

        return respostaCambio.conversion_rate;
    }
}
