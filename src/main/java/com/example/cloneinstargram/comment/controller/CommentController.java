package com.example.cloneinstargram.comment.controller;

import com.example.cloneinstargram.comment.dto.request.CommentRequestDto;
import com.example.cloneinstargram.comment.dto.response.CommentResponseDto;
import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.comment.repository.CommentRepository;
import com.example.cloneinstargram.comment.service.CommentService;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final FeedRepository feedRepository;

    @PostMapping("/{feedId}/comment")
    public CommentResponseDto createComment(@PathVariable Long feedId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return commentService.createComment ( feedId, commentRequestDto, userDetailsImpl );
    }

    @GetMapping("/comment")
    public List<Comment> getComment() {
        return commentRepository.findAllByOrderByCreatedAtDesc ();
    }

    @DeleteMapping("comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        commentService.deleteComment ( commentId, userDetailsImpl );
        return "댓글 삭제 완료";
    }
}
