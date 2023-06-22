package com.intervlgo.ourfolio.repository;

import com.intervlgo.ourfolio.dto.PortfolioDto;
import com.intervlgo.ourfolio.entity.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PortfolioRepositoryCustom {
    Page<Portfolio> searchPortfolio(Pageable pageable, String username, String userId,
                                    String region, String occupation, Long viewCnt,
                                    LocalDateTime from, LocalDateTime to);
}
