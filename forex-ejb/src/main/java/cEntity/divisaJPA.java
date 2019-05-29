/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cEntity;

import entityExceptions.PreexistingEntityException;
import entityExceptions.RollbackFailureException;
import entityExceptions.nullEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import mapeo.Divisas;

/**
 *
 * @author steve
 */
public class divisaJPA implements Serializable {
    public divisaJPA() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx = null;
    private EntityManager emf = null;

    public EntityManager getEntityManager() {
        return emf;
    }

    public void create(Divisas divisas) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            emf.persist(divisas);
            emf.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void edit(Divisas divisas) throws nullEntityException, RollbackFailureException, Exception {
        try {
            emf.getTransaction().begin();
            emf.merge(divisas);
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
            Divisas d;
            try {
                d = emf.getReference(Divisas.class, id);
                d.getIdDivisa();
            } catch (EntityNotFoundException enfe) {
                throw new nullEntityException("The bitacora with id " + id + " no longer exists.", enfe);
            }
            emf.remove(d);
            emf.getTransaction().commit();
        } catch (Exception ex) {            
            throw ex;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public List<Divisas> findDivisasEntities() {
        return findDivisasEntities(true, -1, -1);
    }

    public List<Divisas> findDivisasEntities(int maxResults, int firstResult) {
        return findDivisasEntities(false, maxResults, firstResult);
    }

    private List<Divisas> findDivisasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Divisas.class));
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

    public Divisas findDivisas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Divisas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDivisasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Divisas> rt = cq.from(Divisas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
