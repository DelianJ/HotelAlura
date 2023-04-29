/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.HuespedDao;
import factory.ConnectionFactory;
import java.util.List;
import modelo.Huesped;

/**
 *
 * @author Delian
 */
public class HuespedController {
    private HuespedDao huespeddao;

    public HuespedController() {
        this.huespeddao = new HuespedDao(new ConnectionFactory().recuperaConexion());
    }

    public int guardar(Huesped huesped) {
        return this.huespeddao.guarda(huesped);
    }

    public List<Huesped> buscar(String apellido) {
        return this.huespeddao.buscar(apellido);
    }

    public int modificar(Huesped huesped) {
        return this.huespeddao.modificar(huesped);
    }

    public int modificarIdReserva(int id) {
        int modificarR = this.huespeddao.modificarIdReserva(id);
        if (modificarR >= 0) {
            return modificarR;
        }
        return -1;
    }

    public int eliminar(Integer id) {
        return this.huespeddao.eliminar(id);
    }

    public List<Huesped> listar() {
        return this.huespeddao.listar();
    }
    
}
