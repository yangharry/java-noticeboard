package com.example.javanoticeboard.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.javanoticeboard.model.request.CommentDeleteRequest;
import com.example.javanoticeboard.model.request.CommentPostRequest;
import com.example.javanoticeboard.model.request.CommentUpdateRequest;
import com.example.javanoticeboard.model.response.BoardResponse;
import com.example.javanoticeboard.model.response.CommentResponse;
import com.example.javanoticeboard.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("comment")
    public BoardResponse writeComment(@RequestBody CommentPostRequest commentPostRequest) {
        return commentService.writeCommnet(commentPostRequest);
    }

    @PutMapping("comment")
    public CommentResponse updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(commentUpdateRequest);
    }

    @DeleteMapping("comment")
    public String deleteComment(@RequestBody CommentDeleteRequest commentDeleteRequest) {
        return commentService.deleteComment(commentDeleteRequest);
    }

}
