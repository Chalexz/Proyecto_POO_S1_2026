package proyecto.view.gui.operaciones;

import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.componentes.ConstructorCampos;

public class PanelRegistrarMantenimiento extends JPanel{
    private JTextField codigo;
    private JTextField costo;
    private JTextField fecha;
    private JTextField descripcion;
    private JTextArea camResultado;
    private JButton btnRegistrar;

    public PanelRegistrarMantenimiento() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Registrar mantenimiento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(20));

        
        // codigo + costo
        JPanel filaDoble1 = new JPanel();
        filaDoble1.setLayout(new BoxLayout(filaDoble1, BoxLayout.X_AXIS));
        filaDoble1.setBackground(new Color(30, 30, 30));
        filaDoble1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        codigo = ConstructorCampos.crearCampoTexto(12);
        costo = ConstructorCampos.crearCampoTexto(12);

        filaDoble1.add(ConstructorCampos.crearCampoConEtiqueta("Código de la Maquinaria", codigo));
        filaDoble1.add(Box.createHorizontalStrut(25));
        filaDoble1.add(ConstructorCampos.crearCampoConEtiqueta("Costo Mantenimiento", costo));

        panelFormulario.add(filaDoble1);
        panelFormulario.add(Box.createVerticalStrut(15));


        // fecha + Descripcion
        JPanel filaDoble2 = new JPanel();
        filaDoble2.setLayout(new BoxLayout(filaDoble2, BoxLayout.X_AXIS));
        filaDoble2.setBackground(new Color(30, 30, 30));
        filaDoble2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fecha = ConstructorCampos.crearCampoTexto(12);
        descripcion = ConstructorCampos.crearCampoTexto(12);

        filaDoble2.add(ConstructorCampos.crearCampoConEtiqueta("Fecha del Mantenimiento", fecha));
        filaDoble2.add(Box.createHorizontalStrut(25));
        filaDoble2.add(ConstructorCampos.crearCampoConEtiqueta("Descripción del Mantenimiento", descripcion));

        panelFormulario.add(filaDoble2);
        panelFormulario.add(Box.createVerticalStrut(15));

        // Boton Registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBackground(new Color(45, 45, 45));
        btnRegistrar.setPreferredSize(new Dimension(160, 42));
        btnRegistrar.setMaximumSize(new Dimension(160, 42));
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Cuadro de resultado estilo consola (borde por defecto)
        camResultado = new JTextArea(4, 40);
        camResultado.setEditable(false);
        camResultado.setLineWrap(true);
        camResultado.setWrapStyleWord(true);
        camResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        camResultado.setForeground(new Color(170, 170, 170));
        camResultado.setBackground(new Color(22, 22, 22));

        JScrollPane scrollResultado = new JScrollPane(camResultado);
        scrollResultado.setAlignmentX(Component.LEFT_ALIGNMENT);
        int anchoNombre = 900;
        scrollResultado.setPreferredSize(new Dimension(anchoNombre, 120));
        scrollResultado.setMaximumSize(new Dimension(anchoNombre, 120));
        panelFormulario.add(scrollResultado);

        this.add(panelFormulario, BorderLayout.NORTH);
    }

    public JButton getBotonRegistrar(){
        return btnRegistrar;
    }

    public String getCodigo(){
        return codigo.getText().trim();
    }

    public String getCosto(){
        return costo.getText().trim();
    }

    public String getfecha(){
        return fecha.getText().trim();
    }

    public String getDescripcion(){
        return descripcion.getText().trim();
    }

    public void mostrarResultado(String resultado){
        camResultado.setText(resultado);
    }
}