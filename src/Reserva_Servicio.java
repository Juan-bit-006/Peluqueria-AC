import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Reserva_Servicio extends JFrame {

    public JPanel ReservaServicio;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField telefonoField;
    private JTextField fechaField;
    private JTextField horaField;
    private JTextField reservaservicioField;
    private JButton reservarButton;
    private JButton regresarButton;
    private Connection conexion;

    public Reserva_Servicio() {

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReservacion();
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresar();
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

    void realizarReservacion() {
        conectar();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String telefono = telefonoField.getText();
        String fechaString = fechaField.getText();
        String horaString = horaField.getText();
        String reserva_servicio = reservaservicioField.getText();


        if (!validarFecha(fechaString)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Usa DD/MM/AAAA.");
            return;
        }


        String[] fechaPartes = fechaString.split("/");
        int dia = Integer.parseInt(fechaPartes[0]);
        int mes = Integer.parseInt(fechaPartes[1]) - 1;
        int anio = Integer.parseInt(fechaPartes[2]);
        java.sql.Date fechaSql = new java.sql.Date(new java.util.Date(anio - 1900, mes, dia).getTime());


        Time horaSql;
        try {
            horaSql = Time.valueOf(horaString);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato de hora incorrecto. Usa HH:mm:ss.");
            return;
        }

        try {
            // Insertar en la tabla ReservaServicio
            String sqlInsertReserva = "INSERT INTO ReservaServicio (nombre, apellido, telefono, fecha, hora, reserva_servicio) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sqlInsertReserva);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, telefono);
            preparedStatement.setDate(4, fechaSql);
            preparedStatement.setTime(5, horaSql);
            preparedStatement.setString(6, reserva_servicio);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la reserva: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    boolean validarFecha(String fecha) {

        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        return fecha.matches(regex);
    }

    public void regresar() {
        Inicio enlace = new Inicio();
        enlace.mostrarInicio();
        dispose();

    }
 public void mostrarReservaServicio(){
     Reserva_Servicio reserva_servicio1 = new Reserva_Servicio();
     reserva_servicio1.setContentPane(reserva_servicio1.ReservaServicio);
     reserva_servicio1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     reserva_servicio1.setVisible(true);
     reserva_servicio1.pack();
 }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reserva_Servicio reservaServicio = new Reserva_Servicio();
            reservaServicio.setVisible(true);
        });
    }
}