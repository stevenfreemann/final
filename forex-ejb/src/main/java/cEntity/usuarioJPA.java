/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cEntity;

import entityExceptions.IllegalOrphanException;
import entityExceptions.RollbackFailureException;
import entityExceptions.nullEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import mapeo.Historial;
import mapeo.Saldos;
import mapeo.Usuarios;

/**
 *
 * @author steve
 */
public class usuarioJPA {
     public usuarioJPA() {
        
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx = null;
    private EntityManager emf = null;

    public EntityManager getEntityManager() {
        return emf;
    }

    public void create(Usuarios usuarios) throws RollbackFailureException, Exception {
        if (usuarios.getHistorialList() == null) {
            usuarios.setHistorialList(new ArrayList<Historial>());
        }
        if (usuarios.getSaldosList() == null) {
            usuarios.setSaldosList(new ArrayList<Saldos>());
        }
        try {
            emf.getTransaction().begin();
            emf.persist(usuarios);
            emf.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, nullEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            emf.merge(usuarios);
            emf.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, nullEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            Usuarios u;
            try {
                u = emf.getReference(Usuarios.class, id);
                u.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new nullEntityException("The bitacora with id " + id + " no longer exists.", enfe);
            }
            emf.remove(u);
            emf.getTransaction().commit();
        } catch (Exception ex) {            
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }
    
    public Usuarios validarLogin(String usuario, String contrasena){
        
        try{
            Usuarios u= new Usuarios();
            TypedQuery<Usuarios>consultaUser=emf.createNamedQuery("Usuarios.findByNombreDeUsuario", Usuarios.class);
            consultaUser.setParameter("nombreDeUsuario", usuario);
            u = consultaUser.getSingleResult();
            if(u.getClave().equals(contrasena)){
                return u;
            }else{
                return null;
            }
            
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
