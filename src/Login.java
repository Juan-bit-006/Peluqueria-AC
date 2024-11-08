import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    public Container panelEmergente;
    private JPanel panelLogin;
    private JButton Ingresarusuario;
    private JTextField usuarioTextField;
    private JPasswordField passwordField1;
    private JTextField textUsuario;
    private JPasswordField textPass;
    private JButton ingresarBoton;
    Connection conexion;
    Statement st;
    ResultSet rs;

public Login(){

    Ingresarusuario.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            validarUsuario();
        }
    });
}

    void conectar(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ProyectoIntegrador","root","Juan1107842292");

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    void validarUsuario(){
        conectar();
        int validacion=0;
        String apellido = textUsuario.getText();
        String pass = String.valueOf(textPass.getText());
        try{
            st = conexion.createStatement();
            rs= st.executeQuery("select * from empleado where apellido ='"+apellido+"' and pass = '"+pass+"'");
            if(rs.next()){
                validacion=1;
                if(validacion==1){
                    JOptionPane.showMessageDialog(null, "La credenciales del usuario son correctas");
                    Emergente enlace = new Emergente();
                    enlace.mostrarVentanaEmergente();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Las credenciales no son correctas");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());

        }
    }

    public static void main(String[] args) {
        Login login1 = new Login();
        login1.setContentPane(new Login().panelLogin);
        login1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login1.setVisible(true);
        login1.pack();
    }



}