/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.on;


import ec.edu.ups.p2.dao.PersonaDAO;
import ec.edu.ups.p2.modelo.Persona;
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
public class PersonaON {
    
    @Inject
    PersonaDAO personaDAO;
    
    
     public String guardarPersona(Persona p) {
        try {
            Persona pro = new Persona();
            pro.setCedula(p.getCedula());
            pro.setNombre(p.getNombre());
            pro.setEmail(p.getEmail());
            personaDAO.insert(p);
           
            return "Persona Creado";
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
            return "Error persona Creado";
        }
    }
    
    public List<Persona> listarProductos() {
        try {
            return personaDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void actPersona(Persona p){
        try {
            p.setCedula(p.getCedula());
            personaDAO.update(p);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
