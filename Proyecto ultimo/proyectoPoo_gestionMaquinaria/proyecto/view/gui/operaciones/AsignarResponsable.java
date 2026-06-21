package proyecto.view.gui.operaciones;
import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.componentes.ConstructorCampos;


public class AsignarResponsable extends JPanel{
    private JTextField camIdentificacion;
    private JTextField codigoMaquina;
    private JTextArea camResultado;
    private JButton btnAsignar;

    public AsignarResponsable() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Asignar maquina a responsable");
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
        codigoMaquina = ConstructorCampos.crearCampoTexto(12);

        filaDoble.add(ConstructorCampos.crearCampoConEtiqueta("Identificación", camIdentificacion));
        filaDoble.add(Box.createHorizontalStrut(25));
        filaDoble.add(ConstructorCampos.crearCampoConEtiqueta("Código de máquina", codigoMaquina));

        panelFormulario.add(filaDoble);
        panelFormulario.add(Box.createVerticalStrut(15));

        // Boton Registrar
        btnAsignar = new JButton("Registrar");
        btnAsignar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAsignar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAsignar.setForeground(Color.WHITE);
        btnAsignar.setBackground(new Color(45, 45, 45));
        btnAsignar.setPreferredSize(new Dimension(160, 42));
        btnAsignar.setMaximumSize(new Dimension(160, 42));
        panelFormulario.add(btnAsignar);
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

    public JButton getBotonAsignar(){
        return btnAsignar;
    }

    public String leerIdentificacion(){
        return camIdentificacion.getText().trim();
    }

    public String leerCodigoMaquina(){
        return codigoMaquina.getText().trim();
    }

    public void mostrarResultado(String resultado){
        camResultado.setText(resultado);
    }
}