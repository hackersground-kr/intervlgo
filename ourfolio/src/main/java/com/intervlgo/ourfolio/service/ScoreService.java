package com.intervlgo.ourfolio.service;

import com.intervlgo.ourfolio.auth.PrincipalDetailsService;
import com.intervlgo.ourfolio.dto.ScoreAvgDto;
import com.intervlgo.ourfolio.dto.ScoreDto;
import com.intervlgo.ourfolio.entity.Portfolio;
import com.intervlgo.ourfolio.entity.Score;
import com.intervlgo.ourfolio.entity.User;
import com.intervlgo.ourfolio.filter.JwtProvider;
import com.intervlgo.ourfolio.repository.CommentRepository;
import com.intervlgo.ourfolio.repository.PortfolioRepository;
import com.intervlgo.ourfolio.repository.ScoreRepository;
import com.intervlgo.ourfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final ScoreRepository scoreRepository;

    public ResponseEntity<ScoreDto> giveScore(ScoreDto request, String portfolioWriterId, String jwtToken) {
        User user = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();

        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser_UserId(portfolioWriterId);
        if (optionalPortfolio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Portfolio portfolio = optionalPortfolio.get();

        if (scoreRepository.existsByUserAndPortFolio(user, portfolio)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Score score = Score.builder()
                .user(user)
                .portFolio(portfolio)
                .score(request.getScore())
                .build();
        scoreRepository.save(score);

        return ResponseEntity.ok(score.toDto());
    }

    public ResponseEntity<ScoreAvgDto> getScores(String portfolioWriterId) {
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser_UserId(portfolioWriterId);
        if (optionalPortfolio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Portfolio portfolio = optionalPortfolio.get();

        ScoreAvgDto body = new ScoreAvgDto();
        body.setAvgOfScore(scoreRepository.getAverageOfScore(portfolio));
        body.setPortfolio(portfolio.toDto());

        return ResponseEntity.ok(body);
    }
}
