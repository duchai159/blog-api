package com.spring.blog.springbootblogrestapi.repository;

import com.spring.blog.springbootblogrestapi.entity.ConfirmToken;
import com.spring.blog.springbootblogrestapi.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {
    Optional<ConfirmToken> findByToken(String token);
    Optional<ConfirmToken> findConfirmTokenByUser(User user);
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmToken c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
