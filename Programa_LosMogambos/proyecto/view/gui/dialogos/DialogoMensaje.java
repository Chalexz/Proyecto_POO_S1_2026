package proyecto.view.gui.dialogos;

import java.awt.Component;
import javax.swing.JOptionPane;

public class DialogoMensaje {

    public static void mostrarError(Component padre, String mensaje) {

        JOptionPane.showMessageDialog(
                padre,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarExito(Component padre, String mensaje) {

        JOptionPane.showMessageDialog(
                padre,
                mensaje,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAdvertencia(Component padre, String mensaje) {

        JOptionPane.showMessageDialog(
                padre,
                mensaje,
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {

        int opcion = JOptionPane.showConfirmDialog(
                padre,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }
}
