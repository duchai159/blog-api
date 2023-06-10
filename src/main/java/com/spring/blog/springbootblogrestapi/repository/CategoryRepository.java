package com.spring.blog.springbootblogrestapi.repository;

import com.spring.blog.springbootblogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
