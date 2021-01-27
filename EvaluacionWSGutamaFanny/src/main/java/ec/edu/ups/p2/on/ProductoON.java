/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.on;

import ec.edu.ups.p2.dao.ProductoDAO;
import ec.edu.ups.p2.modelo.Producto;
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
public class ProductoON {
    
    @Inject
    ProductoDAO productoDAO;

    public ProductoON() {
       
    }
    
     public String guardarProducto(Producto p) {
 
         try {
            Producto pro = new Producto();
            pro.setNombre(p.getNombre());
            pro.setCodigo(p.getCodigo());
            pro.setStock(p.getStock());
            pro.setPrecio(p.getPrecio());
            productoDAO.insert(p);
            return "Producto Creado";
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
            return "Error Producto Creado";
        }
    }
    
    public List<Producto> listarProductos() {
        try {
            return productoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void actProducto(Producto p){
        try {
            p.setStock(p.getStock() - 1);
            productoDAO.update(p);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Producto buscarProducto(String codigo){
        try {
            return productoDAO.findByCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
