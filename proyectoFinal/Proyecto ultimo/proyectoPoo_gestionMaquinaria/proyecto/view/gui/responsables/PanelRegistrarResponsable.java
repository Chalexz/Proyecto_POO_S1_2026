package proyecto.view.gui.responsables;
import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.componentes.ConstructorCampos;


public class PanelRegistrarResponsable extends JPanel {
    private JTextField camIdentificacion;
    private JTextField camFechaNacimiento;
    private JTextField camNombreCompleto;
    private JTextArea camResultado;
    private JButton btnRegistrar;

    public PanelRegistrarResponsable() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Registrar responsable");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Identificacion + Fecha de nacimiento
        JPanel filaDoble = new JPanel();
        filaDoble.setLayout(new BoxLayout(filaDoble, BoxLayout.X_AXIS));
        filaDoble.setBackground(new Color(30, 30, 30));
        filaDoble.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        camIdentificacion = ConstructorCampos.crearCampoTexto(12);
        camFechaNacimiento = ConstructorCampos.crearCampoTexto(12);

        filaDoble.add(ConstructorCampos.crearCampoConEtiqueta("Identificación", camIdentificacion));
        filaDoble.add(Box.createHorizontalStrut(25));
        filaDoble.add(ConstructorCampos.crearCampoConEtiqueta("Fecha de nacimiento", camFechaNacimiento));

        panelFormulario.add(filaDoble);
        panelFormulario.add(Box.createVerticalStrut(15));

        // Nombre completo
        camNombreCompleto = ConstructorCampos.crearCampoTexto(40);
        JPanel filaSimple = ConstructorCampos.crearCampoConEtiqueta("Nombre completo", camNombreCompleto);
        filaSimple.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(filaSimple);
        panelFormulario.add(Box.createVerticalStrut(20));

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

    public String leerIdentificacion() {
    return camIdentificacion.getText().trim();
    }

    public String leerFechaNacimiento() {
        return camFechaNacimiento.getText().trim();
    }

    public String leerNombreCompleto() {
        return camNombreCompleto.getText().trim();
    }

    public void mostrarResultado(String texto) {
        camResultado.setText(texto);
    }

    public JButton getBotonRegistrar() {
        return btnRegistrar;
    }
}