import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Inicio extends JFrame {


    public Container ReservaServico;
    private JPanel Inicio;
        private JButton ingresarButton;
        private JButton reservaServicioButton;


    public Inicio(){
            ingresarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Login enlace = new Login();
                    enlace.mostrarLogin();
                    dispose();
                }
            });
        reservaServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reserva_Servicio enlace = new Reserva_Servicio();
                enlace.mostrarReservaServicio();
                dispose();
            }
        });
    }


public void mostrarInicio (){
    Inicio inicio2 = new Inicio();
    inicio2.setContentPane(inicio2.Inicio);
    inicio2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inicio2.setVisible(true);
    inicio2.pack();
}

public void mostrarReservaServicio(){
     Inicio inicio3 = new Inicio();
    inicio3.setContentPane(inicio3.Inicio);
    inicio3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inicio3.setVisible(true);
    inicio3.pack();
}

    public static void main(String[] args) {
        Inicio inicio1 = new Inicio();
        inicio1.setContentPane(inicio1.Inicio);
        inicio1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicio1.setVisible(true);
        inicio1.pack();
    }
    }
