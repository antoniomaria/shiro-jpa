package net.sf.lab.shiro.repository;

import java.util.List;
import java.util.Set;

import net.sf.lab.shiro.domain.PostPermission;
import net.sf.lab.shiro.domain.Role;
import net.sf.lab.shiro.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("select p from PostPermission p where p.user.id = :userId")
    List<PostPermission> getPermissions(@Param("userId") Integer userId);

    @Query("select r from Role r left join r.users u where u.id = :userId")
    Set<Role> getRoles(@Param("userId") Integer userId);
}
