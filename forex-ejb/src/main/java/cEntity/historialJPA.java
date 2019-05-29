/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cEntity;

/**
 *
 * @author steve
 */
import entityExceptions.PreexistingEntityException;
import entityExceptions.RollbackFailureException;
import entityExceptions.nullEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import mapeo.Historial;

/**
 *
 * @author fetec
 */
public class historialJPA implements Serializable {

    public historialJPA() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx = null;
    private EntityManager emf = null;

    public EntityManager getEntityManager() {
        return emf;
    }

    public void create(Historial historial) throws RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            emf.persist(historial);
            emf.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void edit(Historial historial) throws nullEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            emf.merge(historial);
            emf.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void destroy(Integer id) throws nullEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            Historial h;
            try {
                h = emf.getReference(Historial.class, id);
                h.getIdHistorial();
            } catch (EntityNotFoundException enfe) {
                throw new nullEntityException("The bitacora with id " + id + " no longer exists.", enfe);
            }
            emf.remove(h);
            emf.getTransaction().commit();
        } catch (Exception ex) {            
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public List<Historial> findHistorialEntities() {
        return findHistorialEntities(true, -1, -1);
    }

    public List<Historial> findHistorialEntities(int maxResults, int firstResult) {
        return findHistorialEntities(false, maxResults, firstResult);
    }

    private List<Historial> findHistorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historial.class));
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

    public Historial findHistorial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historial.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historial> rt = cq.from(Historial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
