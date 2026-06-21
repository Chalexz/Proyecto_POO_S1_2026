package proyecto.view.gui;

import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.Depreciacion.PanelTablasPorResponsable;
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

        PanelRegistrarResponsable PanelRegistrarResponsable = new PanelRegistrarResponsable();
        PanelEliminarResponsable PanelEliminarResponsable = new PanelEliminarResponsable();
        PanelConsultarResponsable PanelConsultarResponsable = new PanelConsultarResponsable();
        PanelRegistrarHerramientaIndustrial PanelRegistrarHerramientaIndustrial = new PanelRegistrarHerramientaIndustrial();
        PanelRegistrarEquipoIndustrial PanelRegistrarEquipoIndustrial = new PanelRegistrarEquipoIndustrial();
        ConsultarDetalladamente ConsultarDetalladamente = new ConsultarDetalladamente();
        AsignarResponsable AsignarResponsable = new AsignarResponsable();
        ListarResponsable ListarResponsable = new ListarResponsable();
        PanelRegistrarMantenimiento PanelRegistrarMantenimiento = new PanelRegistrarMantenimiento();
        MauinariaAsignadaResponsable MauinariaAsignadaResponsable = new MauinariaAsignadaResponsable();
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
