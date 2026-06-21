package proyecto.model;

import proyecto.util.Comparable;
import java.util.ArrayList;

public class Responsable implements Comparable {
    private static final String PREFIJO = "RESP-";
    private static int contador = 1;
    private String codigo;
    private String identificacion;
    private String fechaNacimiento;
    private String nombreCompleto;
    private ArrayList<Maquinaria> maquinarias;

    public Responsable(String pIdentificacion, String pFechaNacimiento, String pNombreCompleto) {
        codigo = PREFIJO + contador;
        contador++;
        identificacion = pIdentificacion;
        fechaNacimiento = pFechaNacimiento;
        nombreCompleto = pNombreCompleto;
        maquinarias = new ArrayList<Maquinaria>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public ArrayList<Maquinaria> getMaquinarias() {
        return maquinarias;
    }

    public void asignarMaquinaria(Maquinaria pMaquinaria) {
        maquinarias.add(pMaquinaria);
        pMaquinaria.setResponsable(this);
    }

    public void removerMaquinaria(Maquinaria pMaquinaria) {
        maquinarias.remove(pMaquinaria);
        pMaquinaria.setResponsable(null);
    }

    public int cantidadMaquinarias() {
        return maquinarias.size();
    }

    public boolean menorQue(Comparable pObj) {
        Responsable otra = (Responsable) pObj;
        return nombreCompleto.compareToIgnoreCase(otra.getNombreCompleto()) < 0;
    }

    public String toString() {
        String msg = "Responsable\n";
        msg += "  Código: " + codigo + "\n";
        msg += "  Identificación: " + identificacion + "\n";
        msg += "  Fecha de nacimiento: " + fechaNacimiento + "\n";
        msg += "  Nombre completo: " + nombreCompleto + "\n";
        msg += "  Cantidad de maquinarias: " + cantidadMaquinarias();
        return msg;
    }
}