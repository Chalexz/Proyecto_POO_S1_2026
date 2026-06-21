package proyecto.model;

public class EquipoIndustrial extends Maquinaria {
    private static final String PREFIJO = "EQP-";
    private static int contador = 1;
    private double peso;
    private double volumen;

    public EquipoIndustrial(double pValor, int pVidaUtil, String pMarca, String pDescripcion, double pPeso, double pVolumen) {
        super(PREFIJO + contador, pValor, pVidaUtil, pMarca, pDescripcion);
        contador++;
        peso = pPeso;
        volumen = pVolumen;
    }

    public double getPeso() {
        return peso;
    }

    public double getVolumen() {
        return volumen;
    }

public String calcularTablaDepreciacion() {
    StringBuilder msg = new StringBuilder("Tabla de depreciación lineal\n\n");
    double valorInicial = valor;
    double depreciacionAnual = valor / vidaUtil;
    double depreciacionAcumulada = 0.0;
    double valorFinal;

    String formatoEncabezado = "%-5s %-15s %-22s %-25s %-15s%n";
    String formatoFila = "%-5d $%-14.2f $%-21.2f $%-24.2f $%-14.2f%n";

    msg.append(String.format(formatoEncabezado,"Año", "Valor inicial", "Depreciación anual", "Depreciación acumulada", "Valor final"));

    for (int anno = 1; anno <= vidaUtil; anno++) {
        depreciacionAcumulada += depreciacionAnual;
        valorFinal = valorInicial - depreciacionAnual;

        if (Math.abs(valorFinal) < 0.01) {
            valorFinal = 0.0;
        }

        msg.append(String.format(formatoFila, 
                    anno, valorInicial, depreciacionAnual, depreciacionAcumulada, valorFinal));

        valorInicial = valorFinal;
    }

    return msg.toString();
}

    public String toString() {
        String msg = super.toString();
        msg += "\n  Tipo: Equipo industrial";
        msg += "\n  Peso: " + peso;
        msg += "\n  Volumen: " + volumen;
        return msg;
    }
}