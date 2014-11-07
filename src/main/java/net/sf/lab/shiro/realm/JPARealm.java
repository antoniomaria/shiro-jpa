package net.sf.lab.shiro.realm;

import java.util.List;

import javax.persistence.PersistenceException;

import net.sf.lab.shiro.dao.UserDAO;
import net.sf.lab.shiro.dao.support.Permission;
import net.sf.lab.shiro.domain.PostPermission;
import net.sf.lab.shiro.domain.User;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPARealm extends AuthorizingRealm {

    public final static Logger logger = LoggerFactory.getLogger(JPARealm.class);

    @Autowired
    private UserDAO userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        char [] password = upToken.getPassword();

        // Null apikey is invalid
        if (username == null) {
            throw new AccountException("UserName is required");
        }
        User user = null;
        try {
            user = userService.findByUsername(username);
            if (user == null) {
                throw new UnknownAccountException("No account found for userName [" + username + "]");
            }
        } catch (PersistenceException e) {
            final String message = "There was a SQL error while authenticating userName [" + username + "]";
            // Rethrow any SQL errors as an authentication exception
            throw new AuthenticationException(message, e);
        }
        logger.info("Authentication successfully for user {}", user.getFullName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token.getClass().isAssignableFrom(UsernamePasswordToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*-
        Set<Role> roles = userService.getRoles(user.getId());
        for (Role role : roles) {
            info.addRole(role.getName());
        }*/
        List<PostPermission> permissions = userService.getPermissions(user.getId());

        for (Permission permission : permissions) {
            info.addStringPermission(permission.getLiteral());
        }
        return info;
    }
}
