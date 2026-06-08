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
        String msg = "Tabla de depreciación lineal\n";
        double valorInicial = valor;
        double depreciacionAnual = valor / vidaUtil;
        double depreciacionAcumulada = 0.0;
        double valorFinal;

        msg += "Año\tValor inicial\tDepreciación anual\tDepreciación acumulada\tValor final\n";

        for (int anno = 1; anno <= vidaUtil; anno++) {
            depreciacionAcumulada += depreciacionAnual;
            valorFinal = valorInicial - depreciacionAnual;

            msg += anno + "\t";
            msg += valorInicial + "\t";
            msg += depreciacionAnual + "\t";
            msg += depreciacionAcumulada + "\t";
            msg += valorFinal + "\n";

            valorInicial = valorFinal;
        }

        return msg;
    }

    public String toString() {
        String msg = super.toString();
        msg += "\n  Tipo: Equipo industrial";
        msg += "\n  Peso: " + peso;
        msg += "\n  Volumen: " + volumen;
        return msg;
    }
}