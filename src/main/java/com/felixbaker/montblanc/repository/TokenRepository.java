package com.felixbaker.montblanc.repository;

import com.felixbaker.montblanc.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);
}
