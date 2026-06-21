package proyecto.view.gui.responsables;

import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.dialogos.DialogoMensaje;

public class ListarResponsable extends JPanel{
    private JTextArea camResultado;
    
    public ListarResponsable(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Responsables Listados");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(20));

        // Boton Listar
        JButton btnListar = new JButton("Refrescar Listado");
        btnListar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnListar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnListar.setForeground(Color.WHITE);
        btnListar.setBackground(new Color(45, 45, 45));
        btnListar.setPreferredSize(new Dimension(160, 42));
        btnListar.setMaximumSize(new Dimension(160, 42));
        panelFormulario.add(btnListar);
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
        scrollResultado.setPreferredSize(new Dimension(anchoNombre, 320));
        scrollResultado.setMaximumSize(new Dimension(anchoNombre, 320));
        panelFormulario.add(scrollResultado);

        this.add(panelFormulario, BorderLayout.NORTH);

        btnListar.addActionListener(e -> {
            listarResponsables();
        });
    }

    private void listarResponsables() {
        String resultado = camResultado.getText().trim();
        if (resultado.isEmpty()) {
            DialogoMensaje.mostrarAdvertencia(this, "No hay responsables para mostrar. Por favor, registre al menos un responsable.");
            return;
        }
        camResultado.setText("Responsable con identificación '" + resultado + "' encontrado.");
    }
}