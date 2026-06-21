package proyecto.view.gui;

import java.awt.*;
import javax.swing.*;
import proyecto.model.Responsable;
import proyecto.model.Sistema;
import proyecto.view.gui.Depreciacion.PanelTablasPorResponsable;
import proyecto.view.gui.dialogos.DialogoMensaje;
import proyecto.view.gui.maquinarias.ConsultarDetalladamente;
import proyecto.view.gui.maquinarias.ListarTodas;
import proyecto.view.gui.maquinarias.PanelRegistrarEquipoIndustrial;
import proyecto.view.gui.maquinarias.PanelRegistrarHerramientaIndustrial;
import proyecto.view.gui.operaciones.AsignarResponsable;
import proyecto.view.gui.operaciones.MauinariaAsignadaResponsable;
import proyecto.view.gui.operaciones.PanelRegistrarMantenimiento;
import proyecto.view.gui.responsables.ListarResponsable;
import proyecto.view.gui.responsables.PanelConsultarResponsable;
import proyecto.view.gui.responsables.PanelEliminarResponsable;
import proyecto.view.gui.responsables.PanelRegistrarResponsable;

public class GUIPrincipalProyecto {
    public static void main(String[] args) {

        JFrame ventana = new JFrame("Gestión de Maquinaria");
        ventana.setSize(1150,700);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout()); // 5 secciones


        // PANEL SUPERIOR
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(18,18,18));
        panelTitulo.setPreferredSize(new Dimension(1050,60));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JLabel titulo = new JLabel("Sistema de Gestión de Maquinaria");
        titulo.setForeground(new Color(225,225,225));
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        panelTitulo.add(titulo);

        // PANEL LATERAL
        JPanel panelNav = new JPanel();
        panelNav.setBackground(new Color(24,24,24));
        panelNav.setPreferredSize(new Dimension(220,0));
        panelNav.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelNav.setLayout(new BoxLayout(panelNav, BoxLayout.Y_AXIS));
        panelNav.setAlignmentX(Component.LEFT_ALIGNMENT);

        // PANEL CENTRO
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(70,70,70));

        Sistema sistema = new Sistema();

        //--------------------------------------------------------------------------------------
        PanelRegistrarResponsable PanelRegistrarResponsable = new PanelRegistrarResponsable();
        PanelRegistrarResponsable.getBotonRegistrar().addActionListener(e ->{
            String pIdentificacion = PanelRegistrarResponsable.leerIdentificacion();
            String pFechaNacimiento = PanelRegistrarResponsable.leerFechaNacimiento();
            String pNombreCompleto = PanelRegistrarResponsable.leerNombreCompleto();
            if (pIdentificacion.isEmpty() || pFechaNacimiento.isEmpty() || pNombreCompleto.isEmpty()) {
            DialogoMensaje.mostrarAdvertencia(PanelRegistrarResponsable, "Por favor, complete todos los campos.");
            return;
            }
            String resultado = sistema.registrarResponsable(pIdentificacion, pFechaNacimiento, pNombreCompleto);
            PanelRegistrarResponsable.mostrarResultado(resultado);
            DialogoMensaje.mostrarExito(PanelRegistrarResponsable, "Responsable registrado exitosamente.");
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        PanelEliminarResponsable PanelEliminarResponsable = new PanelEliminarResponsable();
        PanelEliminarResponsable.getBotonBuscar().addActionListener(e -> {
            String identificacion = PanelEliminarResponsable.leerIdentificacion();
            if (identificacion.isEmpty()) {
                DialogoMensaje.mostrarAdvertencia(PanelEliminarResponsable, "Por favor, ingrese una identificación.");
                return;
            }
            Responsable r = sistema.buscarResponsablePorIdentificacion(identificacion);
            if (r == null) {
                PanelEliminarResponsable.mostrarResultado("No existe un responsable registrado con esa identificación.");
            } else {
                PanelEliminarResponsable.mostrarResultado("Responsable encontrado: " + r.toString());
                PanelEliminarResponsable.confirmarBusqueda(identificacion);
            }
        });
        PanelEliminarResponsable.getBotonEliminar().addActionListener(e -> {
            String identificacion = PanelEliminarResponsable.obtenerIdentificacionEncontrada();
            if (identificacion == null) {
                DialogoMensaje.mostrarAdvertencia(PanelEliminarResponsable, "Primero debe buscar un responsable existente.");
                return;
            }
            if (DialogoMensaje.confirmar(PanelEliminarResponsable, "¿Está seguro de eliminar al responsable con identificación '" + identificacion + "'?")) {
                boolean exito = sistema.eliminarResponsable(identificacion);
                if (exito) {
                    PanelEliminarResponsable.mostrarResultado("Responsable eliminado exitosamente. Sus maquinarias permanecen registradas sin responsable asignado.");
                } else {
                    PanelEliminarResponsable.mostrarResultado("No se pudo eliminar.");
                }
            }
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        PanelConsultarResponsable PanelConsultarResponsable = new PanelConsultarResponsable();
        PanelConsultarResponsable.getBotonConsultar().addActionListener(e -> {
        String identificacion = PanelConsultarResponsable.leerIdentificacion();
        if (identificacion.isEmpty()) {
            DialogoMensaje.mostrarAdvertencia(PanelConsultarResponsable, "Por favor, ingrese una identificación.");
            return;
        }
        Responsable r = sistema.buscarResponsablePorIdentificacion(identificacion);
        if (r == null) {
            PanelConsultarResponsable.mostrarResultado("No existe un responsable registrado con esa identificación.");
        } else {
            PanelConsultarResponsable.mostrarResultado(r.toString());
        }
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        PanelRegistrarHerramientaIndustrial PanelRegistrarHerramientaIndustrial = new PanelRegistrarHerramientaIndustrial();
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        PanelRegistrarEquipoIndustrial PanelRegistrarEquipoIndustrial = new PanelRegistrarEquipoIndustrial();
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        ConsultarDetalladamente ConsultarDetalladamente = new ConsultarDetalladamente();
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        AsignarResponsable AsignarResponsable = new AsignarResponsable();
        //--------------------------------------------------------------------------------------
        
        //--------------------------------------------------------------------------------------
        ListarResponsable ListarResponsable = new ListarResponsable();
        ListarResponsable.getBotonListar().addActionListener(e ->{
            String resultado = sistema.listarResponsablesAlfabeticamente().toString();
            ListarResponsable.mostrarResultado(resultado);
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        PanelRegistrarMantenimiento PanelRegistrarMantenimiento = new PanelRegistrarMantenimiento();
        PanelRegistrarMantenimiento.getBotonRegistrar().addActionListener(e -> {
        String codigoMaq = PanelRegistrarMantenimiento.getCodigo();
        String costoTexto = PanelRegistrarMantenimiento.getCosto();
        String fecha = PanelRegistrarMantenimiento.getfecha();
        String descripcion = PanelRegistrarMantenimiento.getDescripcion();

        if (codigoMaq.isEmpty() || costoTexto.isEmpty() || fecha.isEmpty() || descripcion.isEmpty()) {
            DialogoMensaje.mostrarAdvertencia(PanelRegistrarMantenimiento, "Por favor, complete todos los campos.");
            return;
        }
        try {
            double costo = Double.parseDouble(costoTexto);
            boolean exito = sistema.registrarMantenimiento(codigoMaq, costo, fecha, descripcion);
            if (exito) {
                PanelRegistrarMantenimiento.mostrarResultado("Mantenimiento registrado exitosamente para la maquinaria '" + codigoMaq + "'.");
            } else {
                PanelRegistrarMantenimiento.mostrarResultado("No existe una maquinaria con ese código.");
            }
        } catch (NumberFormatException ex) {
            DialogoMensaje.mostrarAdvertencia(PanelRegistrarMantenimiento, "El costo debe ser numérico.");
        }
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        MauinariaAsignadaResponsable MaquinariaAsignadaResponsable = new MauinariaAsignadaResponsable();
        MaquinariaAsignadaResponsable.getBotonConsultar().addActionListener(e -> {
            String identificacion = MaquinariaAsignadaResponsable.leerIdentificacion();
            if (identificacion.isEmpty()) {
                DialogoMensaje.mostrarAdvertencia(MaquinariaAsignadaResponsable, "Por favor, ingrese una identificación.");
                return;
            }
            Responsable r = sistema.buscarResponsablePorIdentificacion(identificacion);
            if (r == null) {
                MaquinariaAsignadaResponsable.mostrarResultado("No existe un responsable registrado con esa identificación.");
            } else if (r.cantidadMaquinarias() == 0) {
                MaquinariaAsignadaResponsable.mostrarResultado("El responsable no tiene maquinarias asignadas.");
            } else {
                MaquinariaAsignadaResponsable.mostrarResultado(r.getMaquinarias().toString());
            }
        });
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        ListarTodas ListarTodas = new ListarTodas();
        PanelTablasPorResponsable PanelTablasPorResponsable = new PanelTablasPorResponsable();
        centro.add(PanelRegistrarResponsable, BorderLayout.CENTER);

        // OPCIONES
        String[][] opciones = {
            {"Responsables",
            "Registrar",
            "Consultar Res.",
            "Listar",
            "Eliminar"},
        
            {"Maquinaria",
            "Reg. Equipo",
            "Reg. Herramienta",
            "Consul. Detalladamente",
            "Listar Todas"},
        
            {"Operaciones",
            "Asignar a Responsable",
            "Mantenimiento",
            "Maq. Asig. Responsable"},
        
            {"Depreciación",
            "Tablas Responsables"}
        };

            
        for (String[] opcion : opciones) {
            JPanel grupo = new JPanel();
            grupo.setLayout(new BorderLayout());
            grupo.setBackground(new Color(24,24,24));
            
            String tituloOpciones = opcion[0];
            
            JLabel lbl = new JLabel(tituloOpciones);
            lbl.setForeground(new Color(70,70,70));
            lbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
            lbl.setBorder(BorderFactory.createEmptyBorder(1, 15, 8, 0));

            grupo.add(lbl, BorderLayout.NORTH);
            
            JPanel panelBotones = new JPanel();
            panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
            panelBotones.setBackground(new Color(24,24,24));
                
            for (int i = 1; i < opcion.length; i++){
                String conta = opcion[i];
            
                JButton btn = new JButton(opcion[i]);
                btn.setBackground(new Color(32,32,32));
                btn.setForeground(new Color(235,235,235));
                btn.setFont(new Font("Arial", Font.PLAIN, 14));
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(180,40));
                btn.setMaximumSize(new Dimension(180,40));
                
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setBorder(BorderFactory.createLineBorder(new Color(70,70,70)));
                
                btn.addActionListener(e -> {
                    centro.removeAll();

                    switch (conta){

                        case "Registrar":
                            centro.add(PanelRegistrarResponsable, BorderLayout.CENTER);
                            break;
                        case "Consultar Res.":
                            centro.add(PanelConsultarResponsable, BorderLayout.CENTER);
                            break;
                        case "Eliminar":
                            centro.add(PanelEliminarResponsable, BorderLayout.CENTER);
                            break;
                        case "Reg. Equipo":
                            centro.add(PanelRegistrarEquipoIndustrial, BorderLayout.CENTER);
                            break;
                        case "Reg. Herramienta":
                            centro.add(PanelRegistrarHerramientaIndustrial, BorderLayout.CENTER);
                            break;
                        case "Consul. Detalladamente":
                            centro.add(ConsultarDetalladamente, BorderLayout.CENTER);
                            break;
                        case "Asignar a Responsable":
                            centro.add(AsignarResponsable, BorderLayout.CENTER);
                            break;
                        case "Mantenimiento":
                            centro.add(PanelRegistrarMantenimiento, BorderLayout.CENTER);
                            break;
                        case "Maq. Asig. Responsable":
                            centro.add(MauinariaAsignadaResponsable, BorderLayout.CENTER);
                            break;
                        case "Listar":
                            centro.add(ListarResponsable, BorderLayout.CENTER);
                            break;
                        case "Listar Todas":
                            centro.add(ListarTodas, BorderLayout.CENTER);
                            break;
                        case "Tablas Responsables":
                            centro.add(PanelTablasPorResponsable, BorderLayout.CENTER);
                            break;
                    }
                    centro.revalidate();
                    centro.repaint();
                });
                
                panelBotones.add(btn);
                panelBotones.add(Box.createVerticalStrut(3));
            }
            
            grupo.add(panelBotones, BorderLayout.CENTER);
            panelNav.add(grupo);
            panelNav.add(Box.createVerticalStrut(4));
        }
    
        // ENSAMBLAR
        ventana.add(panelTitulo, BorderLayout.NORTH);
        ventana.add(panelNav, BorderLayout.WEST);
        ventana.add(centro, BorderLayout.CENTER);
        ventana.setVisible(true);

    }

}
