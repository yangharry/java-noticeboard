package com.example.javanoticeboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.javanoticeboard.model.DeleteStatus;
import com.example.javanoticeboard.model.entity.Board;
import com.example.javanoticeboard.model.entity.Comment;
import com.example.javanoticeboard.model.request.CommentDeleteRequest;
import com.example.javanoticeboard.model.request.CommentPostRequest;
import com.example.javanoticeboard.model.request.CommentUpdateRequest;
import com.example.javanoticeboard.model.response.BoardResponse;
import com.example.javanoticeboard.model.response.CommentResponse;
import com.example.javanoticeboard.repository.BoardRepository;
import com.example.javanoticeboard.repository.CommentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public BoardResponse writeCommnet(CommentPostRequest request) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        board.addComment(request.getCommentBody());
        boardRepository.save(board);

        return BoardResponse.from(board);
    }

    @Transactional
    public CommentResponse updateComment(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.getCommentNo())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다"));

        if (comment.getDeleteStatus() == DeleteStatus.DELETE) {
            throw new RuntimeException("삭제된 댓글은 수정할 수 없습니다");
        }

        comment.setBody(request.getCommentBody());

        return CommentResponse.from(commentRepository.save(comment));
    }

    @Transactional
    public String deleteComment(CommentDeleteRequest request) {
        Optional<Comment> commentOprtional = commentRepository.findById(request.getCommentNo());
        Comment comment = commentOprtional.orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));

        commentRepository.delete(comment);
        return "commentNo :" + " " + request.getCommentNo() + ", " + "deleted successfully";
    }
}
