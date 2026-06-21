package proyecto.view.gui.componentes;

import java.awt.*;
import javax.swing.*;

public class ConstructorCampos{
    public static JTextField crearCampoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setForeground(Color.WHITE);
        campo.setBackground(new Color(45, 45, 45));
        campo.setCaretColor(Color.WHITE);
        return campo;
    }

    public static JPanel crearCampoConEtiqueta(String texto, JTextField campo) {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(30, 30, 30));

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(new Color(150, 150, 150));
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 13));
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);

        campo.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenedor.add(etiqueta);
        contenedor.add(Box.createVerticalStrut(6));
        contenedor.add(campo);
        return contenedor;
    }
}