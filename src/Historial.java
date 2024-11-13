import javax.swing.*;

public class Historial extends JFrame{

    private JPanel Historial;
    public Historial(){}

    public void mostrarVentanaEmergente() {
        Historial emergente1 = new Historial();
        emergente1.setContentPane(new Historial().Historial);
        emergente1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emergente1.setVisible(true);
        emergente1.pack();
    }
}