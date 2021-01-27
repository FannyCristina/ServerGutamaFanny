/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.dao;

import ec.edu.ups.p2.modelo.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Fanny
 */

@Stateless
public class PersonaDAO {

    @PersistenceContext(name = "EvaluacionWSGutamaFannyPersistenceUnit")
    private EntityManager em;
    
    
    public boolean insert(Persona persona) throws Exception {
        boolean ban = true;
        try {

            System.out.println("comprobar");
            em.persist(persona);
            ban = true;
        } catch (Exception e) {
            ban = false;
            throw new Exception("Error ingreso producto" + e.getMessage());
        }
        return ban;
    }

    public void delete(Persona persona) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(persona.getId()));
        } catch (Exception e) {
            throw new Exception("Erro Eliminar Producto " + e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("Erro Eliminar Producto " + e.getMessage());
        }
    }

    public void update(Persona persona) throws Exception {
        try {
            em.merge(persona);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " + e.getMessage());
        }
    }

    public Persona read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Persona.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Producto " + e.getMessage());
        }
    }

    public List<Persona> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Producto.findAll");
            List<Persona> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " + e.getMessage());
        }

    }

    public Persona findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Persona.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Persona) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("No se encuentra el id" + e.getMessage());
        }

    }
}
