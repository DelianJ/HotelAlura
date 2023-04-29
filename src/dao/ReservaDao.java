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
import modelo.Reserva;

/**
 *
 * @author Delian
 */
public class ReservaDao {
    
    final private Connection con;
    
    public ReservaDao(Connection recuperaConexion) {
        this.con = recuperaConexion;
    }
    
    public int guarda(Reserva r) {
        try {
            final PreparedStatement state = con.prepareStatement("Insert Into reservas(FechaEntrada, FechaSalida, Valor, FormaPago) Values(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (state) {
                state.setDate(1, r.getFechaEntrada());
                state.setDate(2, r.getFechaSalida());
                state.setInt(3, r.getValor());
                state.setString(4, r.getFormaPago());
                state.execute();
                final ResultSet resulset = state.getGeneratedKeys();
                try (resulset) {
                    while (resulset.next()) {
                        r.setId(resulset.getInt(1));
                        System.out.println("Se guardo la reserva: " + r.getId());
                    }
                    return r.getId();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Reserva> buscar(int index) {
        List<Reserva> resultado = new ArrayList<>();
        try {
            final PreparedStatement state = con.prepareStatement("Select Id, FechaEntrada, FechaSalida, Valor, FormaPago From reservas "
                    + "Where Id = ?");
            try (state) {
                state.setInt(1, index);
                state.execute();
                final ResultSet resultset = state.getResultSet();
                try (resultset) {
                    while (resultset.next()) {
                        Reserva fila = new Reserva(resultset.getInt("Id"), resultset.getDate("FechaEntrada"),
                                resultset.getDate("FechaSalida"), resultset.getInt("Valor"), resultset.getString("FormaPago"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Reserva reserva) {
        try {
            final PreparedStatement state = con.prepareStatement("Update reservas Set FechaEntrada = ?, FechaSalida = ?,"
                    + "Valor = ?, FormaPago = ? Where Id = ?");
            try (state) {
                state.setDate(1, reserva.getFechaEntrada());
                state.setDate(2, reserva.getFechaSalida());
                state.setInt(3, reserva.getValor());
                state.setString(4, reserva.getFormaPago());
                state.setInt(5, reserva.getId());
                state.execute();
                int updatecount = state.getUpdateCount();
                return updatecount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement state = con.prepareStatement("Delete From reservas Where Id = ?");
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

    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();
        try {
            final PreparedStatement state = con.prepareStatement("Select Id, FechaEntrada, FechaSalida, Valor, FormaPago From reservas");
            try (state) {
                state.execute();
                final ResultSet resultset = state.getResultSet();
                try (resultset) {
                    while (resultset.next()) {
                        Reserva fila = new Reserva(resultset.getInt("Id"), resultset.getDate("FechaEntrada"),
                                resultset.getDate("FechaSalida"), resultset.getInt("Valor"), resultset.getString("FormaPago"));
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
