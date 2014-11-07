package net.sf.lab.shiro.domain.support;


import java.io.Serializable;

public interface Persistable  extends Serializable {
    Integer getId();

    boolean isNew();
}
