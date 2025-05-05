import java.util.ArrayList;
import java.util.List;

public class HistoricoConversao {
    private final List<RegistroConversao> registros = new ArrayList<>();

    public void adicionar(RegistroConversao registro) {
        registros.add(registro);
    }

    public void exibir() {
        if (registros.isEmpty()) {
            System.out.println("Nenhuma conversão registrada.");
            return;
        }

        System.out.println("\n=== Histórico de Conversões ===");
        for (RegistroConversao r : registros) {
            System.out.println(r);
        }
    }
}
