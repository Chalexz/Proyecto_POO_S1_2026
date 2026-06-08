package proyecto.model;

public class HerramientaIndustrial extends Maquinaria {
    private static final String PREFIJO = "HER-";
    private static int contador = 1;
    private double valorSalvamento;
    private double tasaDepreciacion;

    public HerramientaIndustrial(double pValor, int pVidaUtil, String pMarca, String pDescripcion, double pValorSalvamento, double pTasaDepreciacion) {
        super(PREFIJO + contador, pValor, pVidaUtil, pMarca, pDescripcion);
        contador++;
        valorSalvamento = pValorSalvamento;
        tasaDepreciacion = pTasaDepreciacion;
    }

    public double getValorSalvamento() {
        return valorSalvamento;
    }

    public double getTasaDepreciacion() {
        return tasaDepreciacion;
    }

    public String calcularTablaDepreciacion() {
        String msg = "Tabla de depreciación decreciente\n";
        double valorInicial = valor;
        double depreciacionAnual;
        double depreciacionAcumulada = 0.0;
        double valorFinal;

        msg += "Año\tValor inicial\tTasa aplicada\tDepreciación anual\tDepreciación acumulada\tValor final\n";

        for (int anno = 1; anno <= vidaUtil; anno++) {
            depreciacionAnual = valorInicial * tasaDepreciacion;
            valorFinal = valorInicial - depreciacionAnual;

            if (valorFinal < valorSalvamento) {
                depreciacionAnual = valorInicial - valorSalvamento;
                valorFinal = valorSalvamento;
            }

            depreciacionAcumulada += depreciacionAnual;

            msg += anno + "\t";
            msg += valorInicial + "\t";
            msg += tasaDepreciacion + "\t";
            msg += depreciacionAnual + "\t";
            msg += depreciacionAcumulada + "\t";
            msg += valorFinal + "\n";

            valorInicial = valorFinal;

            if (valorInicial <= valorSalvamento) {
                break;
            }
        }

        return msg;
    }

    public String toString() {
        String msg = super.toString();
        msg += "\n  Tipo: Herramienta industrial";
        msg += "\n  Valor de salvamento: " + valorSalvamento;
        msg += "\n  Tasa de depreciación: " + tasaDepreciacion;
        return msg;
    }
}