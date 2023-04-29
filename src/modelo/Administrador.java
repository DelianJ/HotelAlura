/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Delian
 */
public class Administrador {
    private Integer id;
    private String nombre;
    private String contrasella;

    public Administrador(String nombre, String contrasella) {
        this.nombre = nombre;
        this.contrasella = contrasella;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasella() {
        return contrasella;
    }
}
