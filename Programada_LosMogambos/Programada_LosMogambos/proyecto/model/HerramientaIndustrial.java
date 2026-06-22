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
    StringBuilder msg = new StringBuilder("Tabla de depreciación decreciente\n\n");
    double valorInicial = valor;
    double depreciacionAnual;
    double depreciacionAcumulada = 0.0;
    double valorFinal;

    String formatoEncabezado = "%-5s %-15s %-15s %-22s %-25s %-15s%n";
    String formatoFila = "%-5d $%-14.2f %-14.2f $%-21.2f $%-24.2f $%-14.2f%n";

    msg.append(String.format(formatoEncabezado,"Año", "Valor inicial", "Tasa aplicada", "Depreciación anual", "Depreciación acumulada", "Valor final"));

    for (int anno = 1; anno <= vidaUtil; anno++) {
        depreciacionAnual = valorInicial * tasaDepreciacion;
        valorFinal = valorInicial - depreciacionAnual;

        if (valorFinal < valorSalvamento) {
            depreciacionAnual = valorInicial - valorSalvamento;
            valorFinal = valorSalvamento;
        }

        depreciacionAcumulada += depreciacionAnual;

        msg.append(String.format(formatoFila, 
                    anno, valorInicial, tasaDepreciacion, depreciacionAnual, depreciacionAcumulada, valorFinal));

        valorInicial = valorFinal;

        if (valorInicial <= valorSalvamento) {
            break;
        }
    }

    return msg.toString();
}

    public String toString() {
        String msg = super.toString();
        msg += "\n  Tipo: Herramienta industrial";
        msg += "\n  Valor de salvamento: " + valorSalvamento;
        msg += "\n  Tasa de depreciación: " + tasaDepreciacion;
        return msg;
    }
}