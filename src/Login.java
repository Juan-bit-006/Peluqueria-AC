import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    public JPanel panelLogin;
    private JLabel Apellido;
    private JLabel pass;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton crearUsuarioButton;
    private JButton regresarButton;
    Connection conexion;
    Statement st;
    ResultSet rs;


    public Login() {

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarUsuario();
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Regresar();
                dispose();
            }
        });
    }

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PeluqueriaAC", "root", "Juan1107842292");

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    void validarUsuario() {
        conectar();
        int validacion = 0;
        String Apellido = textField1.getText();
        String pass = String.valueOf(passwordField1.getText());
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("select * from Empleado where Apellido ='" + Apellido + "' and pass = '" + pass + "'");
            if (rs.next()) {
                validacion = 1;
                if (validacion == 1) {
                    JOptionPane.showMessageDialog(null, "La credenciales del usuario son correctas");
                    Historial enlace = new Historial();
                    enlace.mostrarVentanaEmergente();
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Las credenciales no son correctas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());

        }
    }

    public static void main(String[] args) {
        Login login1 = new Login();
        login1.setContentPane(login1.panelLogin);
        login1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        login1.setVisible(true);
        login1.pack();
    }

    public void Regresar (){
        Inicio enlace = new Inicio();
        enlace.mostrarInicio();
    }



    public void mostrarLogin (){
        {
            Login inicio2 = new Login();
            inicio2.setContentPane(inicio2.panelLogin);
            inicio2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inicio2.setVisible(true);
            inicio2.pack();
        }
    }
}