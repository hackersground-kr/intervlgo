package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.CommentDto;
import com.intervlgo.ourfolio.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{userId}")
    public ResponseEntity<CommentDto> postComment(
            @RequestBody CommentDto commentDto,
            @RequestHeader(name = "Authorization") String jwtToken,
            @PathVariable(name = "userId") String userId
    ) {
        return commentService.postComment(commentDto, jwtToken, userId);
    }

    @GetMapping("/{portfolioWriterId}")
    public ResponseEntity<Page<CommentDto>> getComments(
            Pageable pageable,
            @PathVariable(name="portfolioWriterId") String portfolioWriterId
    ) {
        return commentService.getCommentList(pageable, portfolioWriterId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @RequestBody CommentDto commentDto,
            @RequestHeader(name = "Authorization") String jwtToken,
            @PathVariable(name = "commentId") Long commentId
    ) {
        return commentService.updateComment(commentDto, jwtToken, commentId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(
            @RequestBody CommentDto commentDto,
            @RequestHeader(name = "Authorization") String jwtToken,
            @PathVariable(name = "commentId") Long commentId
    ) {
        return commentService.updateComment(commentDto, jwtToken, commentId);
    }

}
