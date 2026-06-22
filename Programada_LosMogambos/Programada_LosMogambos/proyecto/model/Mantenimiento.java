package proyecto.model;

public class Mantenimiento {
    private double costo;
    private String fecha;
    private String descripcion;

    public Mantenimiento(double pCosto, String pFecha, String pDescripcion) {
        costo = pCosto;
        fecha = pFecha;
        descripcion = pDescripcion;
    }

    public String toString() {
        String msg = "Mantenimiento\n";
        msg += "  Costo: " + costo + "\n";
        msg += "  Fecha: " + fecha + "\n";
        msg += "  Descripción: " + descripcion;
        return msg;
    }
}