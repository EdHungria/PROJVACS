package br.edu.ufra.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class GenericDAO<T> implements InterfaceDAO<T> {

    private EntityManager entityManager;

    public GenericDAO() {
    }

    protected EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = FabricaEntityManager.obterFabrica().createEntityManager();
        }
        return entityManager;
    }

    protected void closeEntityManager() {
        if (entityManager != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean criar(T o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            em.persist(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean excluir(T o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            em.remove(em.merge(o));
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean alterar(T o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            em.merge(o);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public T obter(Class<T> classe, Object id) {
        if (id == null) {
            return null;
        }
        final String JPQL = classe.getSimpleName() + ".findById";
        EntityManager em = getEntityManager();
        final TypedQuery<T> query = em.createNamedQuery(JPQL, classe);
        T resposta = null;
        try {
            List<T> objs = query.setParameter("id", id).getResultList();
            if (objs != null && !objs.isEmpty()) {
                resposta = objs.get(0);
                em.refresh(resposta);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    @Override
    public List<T> obterTodos(Class<T> classe) {
        final String JPQL = classe.getSimpleName() + ".findAll";
        EntityManager em = getEntityManager();
        TypedQuery<T> query = em.createNamedQuery(JPQL, classe);
        List<T> resposta = null;
        try {
            resposta = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
