package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.PortfolioDto;
import com.intervlgo.ourfolio.service.PortfolioService;
import com.intervlgo.ourfolio.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/portfolio")
public class FileController {
    private final PortfolioService portfolioService;

    @GetMapping(value = "/file/{userId}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPDF(@PathVariable(name = "userId") String userId) {
        return portfolioService.getPDF(userId);
    }

    @GetMapping(value = "/file/{userId}/img", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getIMG(@PathVariable(name = "userId") String userId) {
        return portfolioService.getIMG(userId);
    }

    @PostMapping("/file")
    public ResponseEntity<PortfolioDto> postPortfolio(
            @RequestHeader(name = "Authorization") String jwtToken,
            @RequestPart(name = "portfolioDto") PortfolioDto portfolioDto,
            @RequestPart(name="portfolioPDF") MultipartFile portfolioPDF,
            @RequestPart(name="portfolioIMG") MultipartFile portfolioIMG
    ) {
        return portfolioService.updatePortfolio(jwtToken, portfolioDto.getContent(), portfolioDto.getPortFolioPageUrl(), portfolioPDF, portfolioIMG);
    }



}
