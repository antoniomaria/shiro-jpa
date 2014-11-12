package net.sf.lab.shiro.domain;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import net.sf.lab.shiro.domain.support.AbstractPersistable;
import net.sf.lab.shiro.repository.support.Permission;
import net.sf.lab.shiro.types.Perm;

@Entity
public class PostPermission extends AbstractPersistable implements Permission{

    private static final long serialVersionUID = -7271213403942660096L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private Integer mask;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post target;

    public PostPermission() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Post getTarget() {
        return target;
    }

    public void setTarget(Post target) {
        this.target = target;
    }

    public String getLiteral() {
        return new StringBuilder().append(target.getClass().getSimpleName().toLowerCase()).append(":")
                .append(Perm.getLiteral(mask)).append(":").append(this.target.getId()).toString();
    }
}
