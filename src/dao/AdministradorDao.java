/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Administrador;

/**
 *
 * @author Delian
 */
public class AdministradorDao {
    final private Connection con;

    public AdministradorDao(Connection con) {
        this.con = con;
    }
    
    public boolean verificar(Administrador admin){
        System.out.println("Buscando..");
        try{
            String query = "Select * From administrador Where Nombre = ? And Contrasella = ?";
            final PreparedStatement state = con.prepareStatement(query);
            try(state){
                state.setString(1, admin.getNombre());
                state.setString(2, admin.getContrasella());
                state.execute();
                final ResultSet resultset = state.getResultSet();
                try(resultset){
                    if (resultset.next()) {
                        System.out.println("Busqueda terminada");
                        return true;
                    } else {
                        System.out.println("Busqueda terminada");
                        return false;
                    }
                }
            }
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
