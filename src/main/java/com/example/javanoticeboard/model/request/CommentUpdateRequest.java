package com.example.javanoticeboard.model.request;

import lombok.Data;

@Data
public class CommentUpdateRequest {
    private Long commentNo;
    private String commentBody;
}
