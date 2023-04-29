/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import modelo.Huesped;
import modelo.Reserva;

/**
 *
 * @author Delian
 */
public class HuespedDao {

    private Connection con;

    public HuespedDao(Connection recuperaConexion) {
        this.con = recuperaConexion;
    }

    public int guarda(Huesped h) {
        try {
            final PreparedStatement state = con.prepareStatement("Insert Into huespedes(Nombre, Apellido,"
                    + "FechaNacimiento, Nacionalidad, Telefono, IdReserva) Values(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (state) {
                state.setString(1, h.getNombre());
                state.setString(2, h.getApellido());
                state.setDate(3, h.getFechaNacimiento());
                state.setString(4, h.getNacionalidad());
                state.setString(5, h.getTelefono());
                state.setInt(6, h.getIdReserva());
                state.execute();
                final ResultSet resulset = state.getGeneratedKeys();
                try (resulset) {
                    while (resulset.next()) {
                        h.setId(resulset.getInt(1));
                        System.out.println("Se guardo el huesped: " + h.getId());
                    }
                    return h.getId();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Huesped> buscar(String apellido) {
        List<Huesped> resultado = new ArrayList<>();
        try {
            final PreparedStatement state = con.prepareStatement("Select Id, Nombre, Apellido, FechaNacimiento, "
                    + "Nacionalidad, Telefono, IdReserva From huespedes "
                    + "Where Apellido = ?");
            try (state) {
                state.setString(1, apellido);
                state.execute();
                final ResultSet resultset = state.getResultSet();
                try (resultset) {
                    while (resultset.next()) {
                        Huesped fila = new Huesped(resultset.getInt("Id"), resultset.getString("Nombre"),
                                resultset.getString("Apellido"), resultset.getDate("FechaNacimiento"), resultset.getString("Nacionalidad"),
                                resultset.getString("Telefono"), resultset.getInt("IdReserva"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Huesped huesped) {
        try {
            final PreparedStatement state = con.prepareStatement("Update huespedes Set Nombre = ?, Apellido = ?,"
                    + "FechaNacimiento = ?, Nacionalidad = ?, Telefono = ?, IdReserva = ? Where Id = ?");
            try (state) {
                state.setString(1, huesped.getNombre());
                state.setString(2, huesped.getApellido());
                state.setDate(3, huesped.getFechaNacimiento());
                state.setString(4, huesped.getNacionalidad());
                state.setString(5, huesped.getTelefono());
                if (huesped.getIdReserva() == 0) {
                    state.setNull(6, Types.NULL);
                } else {
                    state.setInt(6, huesped.getIdReserva());
                }
                state.setInt(7, huesped.getId());
                state.execute();
                int updatecount = state.getUpdateCount();
                return updatecount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificarIdReserva(int id) {
        try {
            final PreparedStatement state = con.prepareStatement("Update huespedes Set IdReserva = null Where IdReserva = ?");
            try (state) {
                state.setInt(1, id);
                state.execute();
                int updatecount = state.getUpdateCount();
                return updatecount;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement state = con.prepareStatement("Delete From huespedes Where Id = ?");
            try (state) {
                state.setInt(1, id);
                state.execute();
                int updatecount = state.getUpdateCount();
                return updatecount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();
        try {
            final PreparedStatement state = con.prepareStatement("Select Id, Nombre, Apellido, FechaNacimiento, "
                    + "Nacionalidad, Telefono, IdReserva From huespedes ");
            try (state) {
                state.execute();
                final ResultSet resultset = state.getResultSet();
                try (resultset) {
                    while (resultset.next()) {
                        Huesped fila = new Huesped(resultset.getInt("Id"), resultset.getString("Nombre"),
                                resultset.getString("Apellido"), resultset.getDate("FechaNacimiento"), resultset.getString("Nacionalidad"),
                                resultset.getString("Telefono"), resultset.getInt("IdReserva"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
