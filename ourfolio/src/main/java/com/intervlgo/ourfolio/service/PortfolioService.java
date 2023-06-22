package com.intervlgo.ourfolio.service;

import com.intervlgo.ourfolio.dto.PortfolioDto;
import com.intervlgo.ourfolio.entity.Portfolio;
import com.intervlgo.ourfolio.entity.User;
import com.intervlgo.ourfolio.filter.JwtProvider;
import com.intervlgo.ourfolio.repository.PortfolioRepository;
import com.intervlgo.ourfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final BlobService blobService;

    public ResponseEntity<PortfolioDto> postPortfolio(PortfolioDto request, String jwtToken) {
        HttpStatus status = HttpStatus.OK;

        User user = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();

        if (portfolioRepository.existsByUser(user)) {
            status = HttpStatus.CONFLICT;
            return new ResponseEntity<>(status);
        }

        Portfolio portfolio = Portfolio.builder()
                .user(user)
                .content(request.getContent())
                .portfolioPageUrl(request.getPortFolioPageUrl())
                .viewCnt(0L)
                .build();
        portfolioRepository.save(portfolio);

        PortfolioDto body = portfolio.toDto();

        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<PortfolioDto> postPortfolio(PortfolioDto request, String jwtToken, MultipartFile file) {
        HttpStatus status = HttpStatus.OK;

        User user = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();

        if (portfolioRepository.existsByUser(user)) {
            status = HttpStatus.CONFLICT;
            return new ResponseEntity<>(status);
        }

        String savedFileName;
        try {
            savedFileName = blobService.uploadFile(file);
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(status);
        }


        Portfolio portfolio = Portfolio.builder()
                .user(user)
                .content(request.getContent())
                .portfolioPageUrl(request.getPortFolioPageUrl())
                .portfolioFileName(savedFileName)
                .portfolioOriName(file.getOriginalFilename())
                .viewCnt(0L)
                .build();
        portfolioRepository.save(portfolio);

        PortfolioDto body = portfolio.toDto();

        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<Page<PortfolioDto>> getPortfolioes(Pageable pageable, String username, String userId, String region, String occupation,
                                                             Long viewCnt, LocalDateTime from, LocalDateTime to) {
        HttpStatus status = HttpStatus.OK;

        Page<Portfolio> portfolioPage = portfolioRepository.searchPortfolio(pageable, username, userId, region, occupation, viewCnt, from, to);

        Page<PortfolioDto> body = portfolioPage.map(portfolio -> portfolio.toDto());

        return new ResponseEntity<>(body, status);
    }

    @Transactional
    public ResponseEntity<PortfolioDto> getPortfolio(String userId) {
        HttpStatus status = HttpStatus.OK;

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        User user = optionalUser.get();

        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser_UserId(userId);
        if (optionalPortfolio.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        Portfolio portfolio = optionalPortfolio.get();
        portfolio.view();

        PortfolioDto body = portfolio.toDto();

        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<InputStreamResource> getFile(String userId) {
        HttpStatus status = HttpStatus.OK;

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        User user = optionalUser.get();

        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser_UserId(userId);
        if (optionalPortfolio.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        Portfolio portfolio = optionalPortfolio.get();

        try {
            InputStreamResource file = new InputStreamResource(blobService.downloadFile(portfolio.getPortfolioFileName()).getInputStream());
            return new ResponseEntity<>(file, status);
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(status);
        }
    }
}
