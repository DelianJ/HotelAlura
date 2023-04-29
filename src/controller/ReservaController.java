/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReservaDao;
import factory.ConnectionFactory;
import java.util.List;
import modelo.Reserva;

/**
 *
 * @author Delian
 */
public class ReservaController {
    private ReservaDao reservadao;

    public ReservaController() {
        reservadao = new ReservaDao(new ConnectionFactory().recuperaConexion());
    }
    
    public int guarda(Reserva r){
        return reservadao.guarda(r);
    }

    public List<Reserva> buscar(int index) {
        return this.reservadao.buscar(index);
    }

    public int modificar(Reserva reserva) {
        return this.reservadao.modificar(reserva);
    }

    public int eliminar(Integer id) {
        return this.reservadao.eliminar(id);
    }

    public List<Reserva> listar() {
        return this.reservadao.listar();
    }
    
}
