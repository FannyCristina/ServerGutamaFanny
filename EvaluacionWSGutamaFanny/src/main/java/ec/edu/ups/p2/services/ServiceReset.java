/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.services;

import ec.edu.ups.p2.modelo.Producto;
import ec.edu.ups.p2.on.CarroON;
import ec.edu.ups.p2.on.PersonaON;
import ec.edu.ups.p2.on.ProductoON;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Fanny
 */
@Path("/cliente")
public class ServiceReset {

    public ServiceReset() {
    }

    @Inject
    private ProductoON productoON;

    @Inject
    private PersonaON personaON;

    @Inject
    private CarroON carroON;

    private Producto productoaux = new Producto();

    @GET
    @Path("/lista")
    @Produces("application/json")
    public List<Producto> listaProducto() {
        List<Producto> lista = new ArrayList<>();

        lista = productoON.listarProductos();

        return lista;
    }

    @GET
    @Path("/produto")
    @Produces("application/json")
    public String anadirCarrito(@QueryParam("codigo") String codigo, @QueryParam("cantidad") double cantidad) {

        productoaux = buscaProductoCodigo(codigo);
        BigDecimal bd = new BigDecimal(productoaux.getStock() - cantidad);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        productoaux.setStock(bd.intValue());
        productoON.actProducto(productoaux);
        return null;
    }

    public Producto buscaProductoCodigo(String codigo) {
        try {
            //auxCliente = clienteON.buscarCliente(id);
            productoaux = productoON.buscarProducto(codigo);
        } catch (Exception ex) {
            // Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productoaux;
    }
}
