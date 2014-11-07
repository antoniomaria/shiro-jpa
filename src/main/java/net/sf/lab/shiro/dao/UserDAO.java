package net.sf.lab.shiro.dao;

import java.util.List;

import net.sf.lab.shiro.dao.support.Permission;
import net.sf.lab.shiro.domain.PostPermission;
import net.sf.lab.shiro.domain.User;

public interface UserDAO {

    User findByUsername(String username);

    List<PostPermission> getPermissions(Integer userId);

}
