/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.dao;

import ec.edu.ups.p2.modelo.Producto;
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
public class ProductoDAO {

    @PersistenceContext(name = "EvaluacionWSGutamaFannyPersistenceUnit")
    private EntityManager em;

    public boolean insert(Producto producto) throws Exception {
        boolean ban = true;
        try {

            System.out.println("comprobar");
            em.persist(producto);
            ban = true;
        } catch (Exception e) {
            ban = false;
            throw new Exception("Error ingreso producto" + e.getMessage());
        }
        return ban;
    }

    public void delete(Producto producto) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(producto.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " + e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " + e.getMessage());
        }
    }

    public void update(Producto producto) throws Exception {
        try {
            em.merge(producto);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " + e.getMessage());
        }
    }

    public Producto read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Producto.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Producto " + e.getMessage());
        }
    }

    public List<Producto> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Producto.findAll");
            List<Producto> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " + e.getMessage());
        }

    }

    public Producto findByCodigo(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Producto.findByCodigo");
            q.setParameter("id", Integer.parseInt(id));
            return (Producto) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  codigo " + e.getMessage());
        }

    }

}
