import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Historial extends JFrame {
    private JList<String> list1;
    private DefaultListModel<String> modeloLista;
    private Connection conexion;
    private JButton actualizarButton;
    private JButton regresarButton;
    private JLabel imagenLabel;
    public JPanel Historial;

    public Historial() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        list1 = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(list1);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        actualizarButton = new JButton("Actualizar");
        regresarButton = new JButton("Regresar");

        panelBotones.add(actualizarButton);
        panelBotones.add(regresarButton);
        add(panelBotones, BorderLayout.SOUTH);


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloLista.clear();
                cargarReservaciones();
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlLogin();
            }
        });

        cargarReservaciones();
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PeluqueriaAC", "root", "Juan1107842292");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void cargarReservaciones() {
        conectar();
        String query = "SELECT nombre, apellido, telefono, fecha, hora, reserva_servicio FROM ReservaServicio";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                Date fecha = rs.getDate("fecha");
                Time hora = rs.getTime("hora");
                String servicio = rs.getString("reserva_servicio");

                String reservacion = String.format("%s %s - %s - %s %s - %s",
                        nombre, apellido, telefono, fecha, hora, servicio);
                modeloLista.addElement(reservacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las reservaciones: " + e.getMessage());
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

    public void regresarAlLogin() {
        Login login = new Login();
        login.mostrarLogin();
        dispose();
    }

    public void mostrarVentanaEmergente() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Historial historial = new Historial();
            historial.mostrarVentanaEmergente();
        });
    }
}