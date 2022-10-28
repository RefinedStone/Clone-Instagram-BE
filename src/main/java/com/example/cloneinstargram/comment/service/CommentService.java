package com.example.cloneinstargram.comment.service;

import com.example.cloneinstargram.comment.dto.request.CommentRequestDto;
import com.example.cloneinstargram.comment.dto.response.CommentResponseDto;
import com.example.cloneinstargram.comment.entity.Comment;
import com.example.cloneinstargram.comment.repository.CommentRepository;
import com.example.cloneinstargram.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final FeedsRepository feedsRepository;

    public CommentResponseDto createComment(Long feedId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetailsImpl) {
        Feeds feeds = feedsRepository.findById(feedId).orElseThrow(
                () -> new IllegalArgumentException ("해당 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment ( commentRequestDto, feeds, userDetailsImpl.getAccount () );
        commentRepository.save ( comment );
        CommentResponseDto commentResponseDto = new CommentResponseDto ( comment );
        return commentResponseDto;
    }

    public String deleteComment(Long commentId, UserDetailsImpl userDetailsImpl) {
        Comment comment = this.commentRepository.findById ( commentId ).orElseThrow (
                () -> new IllegalArgumentException ("해당 댓글이 존재하지 않습니다.")
        );

        String nickname = comment.getAccount ().getNickname ();

        if(userDetailsImpl.getAccount ().getNickname ().equals ( nickname )) {
            commentRepository.deleteById ( commentId );
            return "댓글 삭제 완료";
        } else {
            throw new RuntimeException ( "작성자만 삭제 가능합니다." );
        }
    }
}
