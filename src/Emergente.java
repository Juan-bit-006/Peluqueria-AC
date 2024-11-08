import javax.swing.*;

public class Emergente extends JFrame{
    private JPanel panelEmergente;
    public Emergente(){}
    public void mostrarVentanaEmergente(){
        Emergente emergente1 = new Emergente();
        emergente1.setContentPane(new Emergente().panelEmergente);
        emergente1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emergente1.setVisible(true);
        emergente1.pack();
    }
}