package com.example.data.base.impl;

import com.example.data.base.CrudBaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository
public class CrudBaseDaoImpl<T, PK extends Serializable> implements CrudBaseDao<T, PK> {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public <S extends T> S save(S s) {
        em.persist(s);
        return s;
    }

    @Transactional
    public <S extends T> S update(S s) {

        return em.merge(s);
    }

    @Transactional
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        List<S> result = new ArrayList<S>();

        if (entities == null) {
            return result;
        }

        for (S entity : entities) {
            result.add(save(entity));
        }

        return result;
    }

    @Transactional
    public void delete(PK id) {
        T entity = findOne(id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Transactional
    public void delete(T entity) {
        em.remove(entity);
    }

    @Transactional
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");

        for (T entity : entities) {
            delete(entity);
        }
    }

    public T findOne(PK id) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) pt.getActualTypeArguments()[0];
        return em.find(tClass, id);
    }

    public boolean exists(PK id) {
        if (findOne(id) != null) {
            return true;
        }
        return false;
    }
}
