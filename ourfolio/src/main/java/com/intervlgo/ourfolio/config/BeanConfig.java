package com.intervlgo.ourfolio.config;


import com.intervlgo.ourfolio.auth.PrincipalDetailsService;
import com.intervlgo.ourfolio.filter.JwtProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final PrincipalDetailsService principalDetailsService;

    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtProvider jwtProvider(){
        return new JwtProvider(principalDetailsService);
    }
    @Bean
    public JPAQueryFactory jpaQueryFactory(){return new JPAQueryFactory(entityManager);}
}
