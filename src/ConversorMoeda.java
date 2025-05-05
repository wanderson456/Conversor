

public class ConversorMoeda {
    private ServicoDeCambio servico;

    public ConversorMoeda(ServicoDeCambio servico) {
        this.servico = servico;
    }

    public double converter(String de, String para, double valor) throws Exception {
        double taxa = servico.obterTaxaDeCambio(de, para);
        return valor * taxa;
    }
}
