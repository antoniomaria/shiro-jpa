package net.sf.lab.shiro.dao.impl;

import org.springframework.stereotype.Repository;

import net.sf.lab.shiro.dao.PostDAO;
import net.sf.lab.shiro.dao.support.AbstractPersistableDAO;
import net.sf.lab.shiro.domain.Post;

@Repository
public class PostDAOImpl extends AbstractPersistableDAO<Post> implements PostDAO {

}
