package com.intervlgo.ourfolio.repository;

import com.intervlgo.ourfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
