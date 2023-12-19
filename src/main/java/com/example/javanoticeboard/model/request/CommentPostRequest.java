package com.example.javanoticeboard.model.request;

import lombok.Data;

@Data
public class CommentPostRequest {
    private Long boardNo;
    private String commentBody;
}
