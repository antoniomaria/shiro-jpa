package net.sf.lab.shiro.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.lab.shiro.domain.support.AbstractPersistable;

@Entity
@Table(name = "users")
public class User extends AbstractPersistable {

    private static final long serialVersionUID = 7209387649701141462L;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String salt;

    @Column(nullable = false)
    private String givenNames;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Lob
    private String profile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private final Set<Role> roles = new HashSet<Role>();

    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    protected void addRole(Role role) {
        roles.add(role);
    }

    @Transient
    public String getFullName() {
        return new StringBuilder().append(givenNames).append(" ").append(surname).toString();
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String username;
        private String password;
        private String givenNames;
        private String surname;
        private String email;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder givenNames(String givenNames) {
            this.givenNames = givenNames;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.username = username;
            user.password = password;
            user.givenNames = givenNames;
            user.surname = surname;
            user.email = email;
            return user;
        }
    }
}
