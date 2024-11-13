import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Inicio extends JFrame {

    public Container panelLogin;
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
        }




    public static void main(String[] args) {
        Inicio inicio1 = new Inicio();
        inicio1.setContentPane(new Inicio().Inicio);
        inicio1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicio1.setVisible(true);
        inicio1.pack();
    }
    }
