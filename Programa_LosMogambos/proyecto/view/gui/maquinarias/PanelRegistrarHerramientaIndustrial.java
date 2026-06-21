package proyecto.view.gui.maquinarias;

import java.awt.*;
import javax.swing.*;
import proyecto.view.gui.componentes.ConstructorCampos;

public class PanelRegistrarHerramientaIndustrial extends JPanel{
    private JTextField Valor;
    private JTextField VidaUtil;
    private JTextField Marca;
    private JTextField Descripcion;
    private JTextField ValorSalvamento;
    private JTextField TasaDepreciacion;
    private JTextArea camResultado;
    private JButton btnRegistrar;

    public PanelRegistrarHerramientaIndustrial() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Titulo
        JLabel titulo = new JLabel("Registrar herramienta industrial");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(20));

        
        // Valor + Vida útil
        JPanel filaDoble1 = new JPanel();
        filaDoble1.setLayout(new BoxLayout(filaDoble1, BoxLayout.X_AXIS));
        filaDoble1.setBackground(new Color(30, 30, 30));
        filaDoble1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Valor = ConstructorCampos.crearCampoTexto(12);
        VidaUtil = ConstructorCampos.crearCampoTexto(12);

        filaDoble1.add(ConstructorCampos.crearCampoConEtiqueta("Valor", Valor));
        filaDoble1.add(Box.createHorizontalStrut(25));
        filaDoble1.add(ConstructorCampos.crearCampoConEtiqueta("Vida útil", VidaUtil));

        panelFormulario.add(filaDoble1);
        panelFormulario.add(Box.createVerticalStrut(15));


        // Marca + Descripcion
        JPanel filaDoble2 = new JPanel();
        filaDoble2.setLayout(new BoxLayout(filaDoble2, BoxLayout.X_AXIS));
        filaDoble2.setBackground(new Color(30, 30, 30));
        filaDoble2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Marca = ConstructorCampos.crearCampoTexto(12);
        Descripcion = ConstructorCampos.crearCampoTexto(12);

        filaDoble2.add(ConstructorCampos.crearCampoConEtiqueta("Marca", Marca));
        filaDoble2.add(Box.createHorizontalStrut(25));
        filaDoble2.add(ConstructorCampos.crearCampoConEtiqueta("Descripción", Descripcion));

        panelFormulario.add(filaDoble2);
        panelFormulario.add(Box.createVerticalStrut(15));

        
        // Valor de salvamento + Tasa de depreciacion
        JPanel filaDoble3 = new JPanel();
        filaDoble3.setLayout(new BoxLayout(filaDoble3, BoxLayout.X_AXIS));
        filaDoble3.setBackground(new Color(30, 30, 30));
        filaDoble3.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        ValorSalvamento = ConstructorCampos.crearCampoTexto(12);
        TasaDepreciacion = ConstructorCampos.crearCampoTexto(12);

        filaDoble3.add(ConstructorCampos.crearCampoConEtiqueta("Valor de salvamento", ValorSalvamento));
        filaDoble3.add(Box.createHorizontalStrut(25));
        filaDoble3.add(ConstructorCampos.crearCampoConEtiqueta("Tasa de depreciación", TasaDepreciacion));

        panelFormulario.add(filaDoble3);
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

    public String leerValor(){
        return Valor.getText().trim();
    }

    public String leerVidaUtil(){
        return VidaUtil.getText().trim();
    }

    public String leerMarca(){
        return Marca.getText().trim();
    }

    public String leerDescripcion(){
        return Descripcion.getText().trim();
    }

    public String leerValorSalvamento(){
        return ValorSalvamento.getText().trim();
    }
    
    public String leerTasaDepreciacion(){
        return TasaDepreciacion.getText().trim();
    }

    public void mostrarResultado(String resultado){
        camResultado.setText(resultado);
    }
}