package net.sf.lab.shiro.repository;

import net.sf.lab.shiro.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Integer>{

}
