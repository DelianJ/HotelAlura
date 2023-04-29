/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AdministradorDao;
import factory.ConnectionFactory;
import modelo.Administrador;

/**
 *
 * @author Delian
 */
public class AdministradorController {
    private AdministradorDao admindao;

    public AdministradorController() {
        this.admindao = new AdministradorDao(new ConnectionFactory().recuperaConexion());
    }
    
    public boolean verificar(Administrador admin){
        return this.admindao.verificar(admin);
    }
}
