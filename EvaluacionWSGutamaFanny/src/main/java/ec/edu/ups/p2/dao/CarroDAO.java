/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.dao;

import ec.edu.ups.p2.modelo.Carro;
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
public class CarroDAO {

    @PersistenceContext(name = "EvaluacionWSGutamaFannyPersistenceUnit")
    private EntityManager em;

    public boolean insert(Carro carro) throws Exception {
        boolean ban = true;
        try {

            System.out.println("comprobar");
            em.persist(carro);
            ban = true;
        } catch (Exception e) {
            ban = false;
            throw new Exception("Error ingreso producto" + e.getMessage());
        }
        return ban;
    }

    public void delete(Carro carro) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(carro.getId()));
        } catch (Exception e) {
            throw new Exception("Erro Eliminar Producto " + e.getMessage());
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

    public void update(Carro carro) throws Exception {
        try {
            em.merge(carro);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " + e.getMessage());
        }
    }

    public Carro read(int id) throws Exception {
        try {
            System.out.println("Comprobar");
            return em.find(Carro.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Producto " + e.getMessage());
        }
    }

    public List<Carro> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Producto.findAll");
            List<Carro> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " + e.getMessage());
        }

    }

    public Carro findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Carro.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Carro) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " + e.getMessage());
        }

    }

    public List<Carro> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Carro.findByCodigocompra");
            q.setParameter("codigocompra", codigo);
            System.out.println("acaa estamos buscando");
            List<Carro> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Carro " + e.getMessage());
        }

    }
}