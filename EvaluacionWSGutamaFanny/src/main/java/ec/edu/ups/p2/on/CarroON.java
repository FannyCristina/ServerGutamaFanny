/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.on;

import ec.edu.ups.p2.dao.CarroDAO;
import ec.edu.ups.p2.dao.PersonaDAO;
import ec.edu.ups.p2.dao.ProductoDAO;
import ec.edu.ups.p2.modelo.Carro;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fanny
 */
@Stateless
public class CarroON {

    @Inject
    ProductoON productoON;

    @Inject
    PersonaON personaON;

    @Inject
    CarroDAO carroDAO;

    public CarroON() {
    }

    public String guardarCarro(Carro c) {
        try {
            Carro cc = new Carro();
            System.out.println("|" + c.getCodigocompra() + "|");

            cc.setPrecio(c.getPrecio());
            cc.setCodigocompra(c.getCodigocompra());
            cc.setProductoId(c.getProductoId());
            cc.setPersonaId(c.getPersonaId());
            productoON.actProducto(c.getProductoId());
            personaON.actPersona(c.getPersonaId());
            carroDAO.insert(cc);
            return "Carrito agregado";
        } catch (Exception ex) {
            Logger.getLogger(CarroON.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro agregar carrito";
        }
    }

    public List<Carro> listarCarro() {
        try {
            return carroDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(CarroON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Carro> listarCarroCodigo(String codigo) {
        try {
            return carroDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(CarroON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String guardarLista(List<Carro> list) {
        try {
            for (Carro c : list) {
                Carro cc = new Carro();
                System.out.println("|" + c.getCodigocompra() + "|");
                cc.setCodigocompra(c.getCodigocompra());
                cc.setPrecio(c.getPrecio());
                cc.setProductoId(c.getProductoId());
                guardarCarro(cc);
                productoON.actProducto(c.getProductoId());
                return "Compra almacenada";
            }
        } catch (Exception ex) {
            Logger.getLogger(CarroON.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro";
        }
        return "Listo";
    }
}
