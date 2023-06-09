package com.spring.blog.springbootblogrestapi.repository;

import com.spring.blog.springbootblogrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
