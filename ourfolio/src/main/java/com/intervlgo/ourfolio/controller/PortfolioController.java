package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.PortfolioDto;
import com.intervlgo.ourfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("")
    public ResponseEntity<PortfolioDto> postPortfolio(
            @RequestBody PortfolioDto portfolioDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return portfolioService.postPortfolio(portfolioDto, jwtToken);
    }

    @PostMapping("/file")
    public ResponseEntity<PortfolioDto> postPortfolio(
            @RequestPart(name = "portfolioDto") PortfolioDto portfolioDto,
            @RequestHeader(name = "Authorization") String jwtToken,
            @RequestPart(name="file") MultipartFile file
            ) {
        return portfolioService.postPortfolio(portfolioDto, jwtToken, file);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PortfolioDto>> getPortfolioList(
            Pageable pageable,
            @RequestParam(name = "writer-username", required = false) String username,
            @RequestParam(name = "writer-userId", required = false) String userId,
            @RequestParam(name ="writer-region", required = false) String region,
            @RequestParam(name = "writer-occupation", required = false) String occupation,
            @RequestParam(name = "view-cnt", required = false) Long viewCnt,
            @RequestParam(name = "from", defaultValue = "2000-01-01 00")String from,
            @RequestParam(name = "to", defaultValue = "3000-12-31 23")String to
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return portfolioService.getPortfolioes(pageable, username, userId,
                region, occupation, viewCnt,
                LocalDateTime.parse(from,formatter), LocalDateTime.parse(to, formatter));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PortfolioDto> getPortfolio(@PathVariable(name = "userId") String userId) {
        return portfolioService.getPortfolio(userId);
    }

    @GetMapping(value = "/file/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> getFile(@PathVariable(name = "userId") String userId) {
        return portfolioService.getFile(userId);
    }


}
