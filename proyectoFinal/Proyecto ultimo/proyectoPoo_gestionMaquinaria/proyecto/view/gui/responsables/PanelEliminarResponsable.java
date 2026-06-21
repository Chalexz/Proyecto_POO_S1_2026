package proyecto.view.gui.responsables;
import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.componentes.ConstructorCampos;


public class PanelEliminarResponsable extends JPanel{
    private JTextField camIdentificacion;
    private String identificacionEncontrada;
    private JTextArea camResultado;
    private JButton btnEliminar;
    private JButton btnBuscar;
    
    public PanelEliminarResponsable() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Eliminar responsable");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Nombre completo
        camIdentificacion = ConstructorCampos.crearCampoTexto(40);
        JPanel filaSimple = ConstructorCampos.crearCampoConEtiqueta("Identificación", camIdentificacion);
        filaSimple.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(filaSimple);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Boton Eliminar
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(45, 45, 45));
        btnEliminar.setPreferredSize(new Dimension(160, 42));
        btnEliminar.setMaximumSize(new Dimension(160, 42));
        panelFormulario.add(btnEliminar);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Boton Buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(45, 45, 45));
        btnBuscar.setPreferredSize(new Dimension(160, 42));
        btnBuscar.setMaximumSize(new Dimension(160, 42));
        panelFormulario.add(btnBuscar);
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

    public String leerIdentificacion(){
        return camIdentificacion.getText().trim();
    }

    public void mostrarResultado(String texto){
        camResultado.setText(texto);
    }

    public JButton getBotonBuscar(){
        return btnBuscar;
    }

    public JButton getBotonEliminar(){
        return btnEliminar;
    }

    public void confirmarBusqueda(String identificacion) {
    identificacionEncontrada = identificacion;
    }

    public String obtenerIdentificacionEncontrada() {
        return identificacionEncontrada;
    }
}