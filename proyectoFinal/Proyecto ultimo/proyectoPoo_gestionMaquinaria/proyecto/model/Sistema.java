package proyecto.model;

import proyecto.util.Ordenamiento;
import java.util.ArrayList;

public class Sistema {
    private ArrayList<Responsable> responsables;
    private ArrayList<Maquinaria> maquinarias;

    public Sistema() {
        responsables = new ArrayList<Responsable>();
        maquinarias = new ArrayList<Maquinaria>();
    }

    public String registrarResponsable(String pIdentificacion, String pFechaNacimiento, String pNombreCompleto) {
        Responsable r = new Responsable(pIdentificacion, pFechaNacimiento, pNombreCompleto);
        responsables.add(r);
        return r.getCodigo();
    }

    public String registrarEquipoIndustrial(double pValor, int pVidaUtil, String pMarca, String pDescripcion, double pPeso, double pVolumen) {
        EquipoIndustrial equipo = new EquipoIndustrial(pValor, pVidaUtil, pMarca, pDescripcion, pPeso, pVolumen);
        maquinarias.add(equipo);
        return equipo.getCodigo();
    }

    public String registrarHerramientaIndustrial(double pValor, int pVidaUtil, String pMarca, String pDescripcion, double pValorSalvamento, double pTasaDepreciacion) {
        HerramientaIndustrial herramienta = new HerramientaIndustrial(pValor, pVidaUtil, pMarca, pDescripcion, pValorSalvamento, pTasaDepreciacion);
        maquinarias.add(herramienta);
        return herramienta.getCodigo();
    }

    public boolean asignarMaquinariaAResponsable(String pIdentificacionResp, String pCodigoMaq) {
        Responsable r = buscarResponsablePorIdentificacion(pIdentificacionResp);
        Maquinaria m = buscarMaquinariaPorCodigo(pCodigoMaq);

        if (r == null || m == null) {
            return false;
        }

        if (m.getResponsable() != null) {
            return false;
        }

        r.asignarMaquinaria(m);
        return true;
    }

    public boolean registrarMantenimiento(String pCodigoMaq, double pCosto, String pFecha, String pDescripcion) {
        Maquinaria m = buscarMaquinariaPorCodigo(pCodigoMaq);

        if (m == null) {
            return false;
        }

        Mantenimiento mantenimiento = new Mantenimiento(pCosto, pFecha, pDescripcion);
        m.registrarMantenimiento(mantenimiento);
        return true;
    }

    public Responsable buscarResponsablePorIdentificacion(String pIdentificacion) {
        for (Responsable r : responsables) {
            if (r.getIdentificacion().equals(pIdentificacion)) {
                return r;
            }
        }

        return null;
    }

    public Maquinaria buscarMaquinariaPorCodigo(String pCodigo) {
        for (Maquinaria m : maquinarias) {
            if (m.getCodigo().equals(pCodigo)) {
                return m;
            }
        }

        return null;
    }

    public ArrayList<Responsable> listarResponsablesAlfabeticamente() {
        ArrayList<Comparable> temporal = new ArrayList<Comparable>();

        for (Responsable r : responsables) {
            temporal.add(r);
        }

        Ordenamiento.ordenar(temporal);

        ArrayList<Responsable> resultado = new ArrayList<Responsable>();

        for (Comparable obj : temporal) {
            resultado.add((Responsable) obj);
        }

        return resultado;
    }

    public ArrayList<Maquinaria> listarMaquinariasPorVidaUtil() {
        ArrayList<Comparable> temporal = new ArrayList<Comparable>();

        for (Maquinaria m : maquinarias) {
            temporal.add(m);
        }

        Ordenamiento.ordenar(temporal);

        ArrayList<Maquinaria> resultado = new ArrayList<Maquinaria>();

        for (Comparable obj : temporal) {
            resultado.add((Maquinaria) obj);
        }

        return resultado;
    }

    public boolean eliminarResponsable(String pIdentificacion) {
        Responsable r = buscarResponsablePorIdentificacion(pIdentificacion);

        if (r == null) {
            return false;
        }

        while (r.cantidadMaquinarias() > 0) {
            r.removerMaquinaria(r.getMaquinarias().get(0));
        }

        responsables.remove(r);
        return true;
    }
}