package proyecto.model;

import java.util.ArrayList;

public abstract class Maquinaria implements Comparable {
    protected String codigo;
    protected double valor;
    protected int vidaUtil;
    protected String marca;
    protected String descripcion;
    private Responsable responsable;
    private ArrayList<Mantenimiento> mantenimientos;

    public Maquinaria(String pCodigo, double pValor, int pVidaUtil, String pMarca, String pDescripcion) {
        codigo = pCodigo;
        valor = pValor;
        vidaUtil = pVidaUtil;
        marca = pMarca;
        descripcion = pDescripcion;
        responsable = null;
        mantenimientos = new ArrayList<Mantenimiento>();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public String getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public ArrayList<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setResponsable(Responsable pResponsable) {
        responsable = pResponsable;
    }

    public void registrarMantenimiento(Mantenimiento pMantenimiento) {
        mantenimientos.add(pMantenimiento);
    }

    public boolean menorQue(Comparable pObj) {
        Maquinaria otra = (Maquinaria) pObj;
        return vidaUtil < otra.getVidaUtil();
    }

    public double getValor() {
        return valor;
    }

    public abstract String calcularTablaDepreciacion();

    public String toString() {
        String msg = "Maquinaria\n";
        msg += "  Código: " + codigo + "\n";
        msg += "  Valor: " + valor + "\n";
        msg += "  Vida útil: " + vidaUtil + "\n";
        msg += "  Marca: " + marca + "\n";
        msg += "  Descripción: " + descripcion + "\n";

        if (responsable != null) {
            msg += "  Responsable: " + responsable.getNombreCompleto() + "\n";
        } else {
            msg += "  Responsable: sin asignar\n";
        }

        msg += "  Cantidad de mantenimientos: " + mantenimientos.size();
        return msg;
    }
}