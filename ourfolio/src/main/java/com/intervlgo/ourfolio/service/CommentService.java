package com.intervlgo.ourfolio.service;

import com.intervlgo.ourfolio.dto.CommentDto;
import com.intervlgo.ourfolio.entity.Comment;
import com.intervlgo.ourfolio.entity.Portfolio;
import com.intervlgo.ourfolio.entity.User;
import com.intervlgo.ourfolio.filter.JwtProvider;
import com.intervlgo.ourfolio.repository.CommentRepository;
import com.intervlgo.ourfolio.repository.PortfolioRepository;
import com.intervlgo.ourfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<CommentDto> postComment(CommentDto request, String jwtToken, String portfolioWriterId) {
        User portfolioWriter = userRepository.findByUserId(portfolioWriterId).get();
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser(portfolioWriter);
        if (optionalPortfolio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Portfolio portfolio = optionalPortfolio.get();

        User commentWriter = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();

        Comment comment = Comment.builder()
                .user(commentWriter)
                .portfolio(portfolio)
                .content(request.getContent())
                .build();
        commentRepository.save(comment);

        CommentDto body = comment.toDto();

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Page<CommentDto>> getCommentList(Pageable pageable, String portfolioWriterId) {
        User portfolioWriter = userRepository.findByUserId(portfolioWriterId).get();
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUser(portfolioWriter);
        if (optionalPortfolio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Portfolio portfolio = optionalPortfolio.get();

        Page<Comment> commentPage = commentRepository.findByPortfolio(portfolio, pageable);

        Page<CommentDto> body = commentPage.map(Comment::toDto);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<CommentDto> updateComment(CommentDto request, String jwtToken, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        User requester = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();
        if(comment.getUser().equals(requester)) {
            comment.update(request.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CommentDto body = comment.toDto();
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<CommentDto> deleteComment(String jwtToken, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        User requester = userRepository.findByUserId(jwtProvider.getId(jwtToken)).get();
        if(comment.getUser().equals(requester)) {
            commentRepository.delete(comment);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CommentDto body = comment.toDto();
        return ResponseEntity.ok(body);
    }
}
