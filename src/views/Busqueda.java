package views;

import com.sun.source.tree.IfTree;
import controller.HuespedController;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import modelo.Reserva;
import controller.ReservaController;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.Huesped;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane, btnbuscar, btnEditar, btnEliminar;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    private JTabbedPane panel;
    private ReservaController reservacontroller;
    private HuespedController huespedcontroller;
    private int indexPanel;
    int xMouse, yMouse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 20));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        btnbuscar = new JPanel();
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        cargarDatosReserva();
        cargarDatoshuesped();
        configuraBotones();
    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void configuraBotones() {
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexPanel = panel.getSelectedIndex();
                if (indexPanel == 0) {
                    buscarReserva();
                } else if (indexPanel == 1) {
                    buscarHuesped();
                }
            }
        });

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                indexPanel = panel.getSelectedIndex();
                if (indexPanel == 0) {
                    modificaReserva();
                } else if (indexPanel == 1) {
                    modificaHuesped();
                }
            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                indexPanel = panel.getSelectedIndex();
                if (indexPanel == 0) {
                    eliminarReserva();
                } else if (indexPanel == 1) {
                    eliminarhuesped();
                }
            }
        });
    }

    private void cargarDatosReserva() {
        reservacontroller = new ReservaController();
        List<Reserva> reservas = reservacontroller.listar();
        modelo.setRowCount(0);
        reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.getId(), reserva.getFechaEntrada().toLocalDate().toString(),
            reserva.getFechaSalida().toLocalDate().toString(), reserva.getValor(), reserva.getFormaPago()}));
    }

    private void cargarDatoshuesped() {
        huespedcontroller = new HuespedController();
        List<Huesped> huespedes = huespedcontroller.listar();
        modeloHuesped.setRowCount(0);
        huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[]{huesped.getId(), huesped.getNombre(),
            huesped.getApellido(), huesped.getFechaNacimiento().toLocalDate().toString(), huesped.getNacionalidad(),
            huesped.getTelefono(), huesped.getIdReserva()}));
    }

    private void buscarReserva() {
        try {
            int index = Integer.parseInt(txtBuscar.getText());
            modelo.setRowCount(0);
            reservacontroller = new ReservaController();
            List<Reserva> reservas = reservacontroller.buscar(index);
            if (!reservas.isEmpty()) {
                reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.getId(), reserva.getFechaEntrada().toLocalDate().toString(),
                    reserva.getFechaSalida().toLocalDate().toString(), reserva.getValor(), reserva.getFormaPago()}));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ninguna reserva conn el numero: " + txtBuscar.getText());
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Por favor introduca un número de reserva valido");
        }
    }

    private void buscarHuesped() {
        modeloHuesped.setRowCount(0);
        huespedcontroller = new HuespedController();
        List<Huesped> huespedes = huespedcontroller.buscar(txtBuscar.getText());
        if (!huespedes.isEmpty()) {
            huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[]{huesped.getId(), huesped.getNombre(),
                huesped.getApellido(), huesped.getFechaNacimiento().toLocalDate().toString(), huesped.getNacionalidad(),
                huesped.getTelefono(), huesped.getIdReserva()}));
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ningun huesped con apellido: " + txtBuscar.getText());
        }
    }

    private void modificaReserva() {
        if (seleccionReserva()) {
            JOptionPane.showMessageDialog(null, "Por favor elija una reserva");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    Date fechaE = Date.valueOf((String) modelo.getValueAt(tbReservas.getSelectedRow(), 1));
                    Date fechaS = Date.valueOf((String) modelo.getValueAt(tbReservas.getSelectedRow(), 2));
                    Integer valor = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
                    String formaP = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
                    int filasActualizadas;
                    filasActualizadas = this.reservacontroller.modificar(new Reserva(id, fechaE, fechaS, valor, formaP));
                    if (filasActualizadas != 1) {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar los datos\nPor favor intente de nuevo");
                    } else {
                        String mensaje = "La reserva con numero: " + id + " fue modificada con éxito!";
                        mensajeExito(mensaje);
                    }
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        cargarDatosReserva();
    }

    private void modificaHuesped() {
        if (tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor elija un huesped");
            return;
        }
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
                    String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
                    Date fechaN = Date.valueOf(tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                    String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
                    String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
                    Integer idR = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                    int filasActualizadas;
                    filasActualizadas = this.huespedcontroller.modificar(new Huesped(id, nombre, apellido, fechaN, nacionalidad, telefono, idR));
                    if (filasActualizadas != 1) {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar los datos\nPor favor intente de nuevo");
                    } else {
                        String mensaje = "El huesped con: " + id + " fue editado con éxito!";
                        mensajeExito(mensaje);
                    }
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        cargarDatoshuesped();
    }

    private void eliminarReserva() {
        if (seleccionReserva()) {
            JOptionPane.showMessageDialog(null, "Por favor elija una reserva");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    huespedcontroller = new HuespedController();
                    int filasActualizadas = huespedcontroller.modificarIdReserva(id);
                    if (filasActualizadas >= 0) {
                        int filasEliminadas;
                        reservacontroller = new ReservaController();
                        filasEliminadas = this.reservacontroller.eliminar(id);
                        if (filasEliminadas != 1) {
                            JOptionPane.showMessageDialog(this, "Ocurrio un error al eliminar la reserva\nPor favor intente de nuevo");
                        } else {
                            String mensaje = "La reserva numero: " + id + " fue eliminada con éxito!";
                            mensajeExito(mensaje);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al elimar la reserva\nPor favor intente de nuevo");
                    }

                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        cargarDatosReserva();
        cargarDatoshuesped();
    }

    private void eliminarhuesped() {
        if (seleccionHuesped()) {
            JOptionPane.showMessageDialog(null, "Por favor elija un huesped");
            return;
        }
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    huespedcontroller = new HuespedController();
                    int filasEliminadas;
                    filasEliminadas = huespedcontroller.eliminar(id);
                    if (filasEliminadas == 1) {
                        String mensaje = "El huesped con ID: " + id + " fue eliminado con éxito!";
                        mensajeExito(mensaje);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al elimar el huesped\nPor favor intente de nuevo");
                    }
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        cargarDatoshuesped();
    }

    private boolean seleccionReserva() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }

    private boolean seleccionHuesped() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private void mensajeExito(String mensaje) {
        Exito exi = new Exito(mensaje);
        exi.setVisible(true);
        dispose();
    }
}
