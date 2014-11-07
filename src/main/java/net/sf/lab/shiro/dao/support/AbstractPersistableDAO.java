package net.sf.lab.shiro.dao.support;

import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.lab.shiro.domain.support.Persistable;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractPersistableDAO<T extends Persistable> implements PersistableDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T findById(Integer id) {
        return entityManager.find(returnedClass(), id);
    }

   
    public Class<T> returnedClass() {
        Type[] parameterizedTypes = ReflectionUtil.getParameterizedTypes(this);
        Class<T> clazz;
        try {
            clazz = (Class<T>) ReflectionUtil.getClass(parameterizedTypes[0]);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return clazz;
    }

}
