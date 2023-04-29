/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Delian
 */
public class ConnectionFactory {
    private DataSource datasourse;
    public ConnectionFactory(){
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotelalura?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("1234asd");
        pooledDataSource.setMaxPoolSize(10);
        
        this.datasourse = pooledDataSource;
    }
    public Connection recuperaConexion(){
        try {
            return this.datasourse.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
