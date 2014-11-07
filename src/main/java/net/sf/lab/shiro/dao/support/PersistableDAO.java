package net.sf.lab.shiro.dao.support;

import net.sf.lab.shiro.domain.support.Persistable;


public interface PersistableDAO<T extends Persistable>{

    T findById(Integer id);

}
