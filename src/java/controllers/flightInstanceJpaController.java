/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entity.flightInstance;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alex
 */
public class flightInstanceJpaController implements Serializable {

    public flightInstanceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(flightInstance flightInstance) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(flightInstance);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(flightInstance flightInstance) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flightInstance = em.merge(flightInstance);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = flightInstance.getId();
                if (findflightInstance(id) == null) {
                    throw new NonexistentEntityException("The flightInstance with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flightInstance flightInstance;
            try {
                flightInstance = em.getReference(flightInstance.class, id);
                flightInstance.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flightInstance with id " + id + " no longer exists.", enfe);
            }
            em.remove(flightInstance);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<flightInstance> findflightInstanceEntities() {
        return findflightInstanceEntities(true, -1, -1);
    }

    public List<flightInstance> findflightInstanceEntities(int maxResults, int firstResult) {
        return findflightInstanceEntities(false, maxResults, firstResult);
    }

    private List<flightInstance> findflightInstanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(flightInstance.class));
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

    public flightInstance findflightInstance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(flightInstance.class, id);
        } finally {
            em.close();
        }
    }

    public int getflightInstanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<flightInstance> rt = cq.from(flightInstance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
