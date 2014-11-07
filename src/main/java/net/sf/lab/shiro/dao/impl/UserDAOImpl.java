package net.sf.lab.shiro.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import net.sf.lab.shiro.dao.UserDAO;
import net.sf.lab.shiro.dao.support.AbstractPersistableDAO;
import net.sf.lab.shiro.dao.support.Permission;
import net.sf.lab.shiro.domain.Post;
import net.sf.lab.shiro.domain.PostPermission;
import net.sf.lab.shiro.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractPersistableDAO<Post> implements UserDAO {

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostPermission> getPermissions(Integer userId) {
        TypedQuery<PostPermission> query = entityManager.createQuery("select p where PostPermission p p.user.id = ?",
                PostPermission.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

}
