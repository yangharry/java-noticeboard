package com.example.javanoticeboard.model.request;

import lombok.Data;

@Data
public class BoardPostRequest {
    private String title;
    private String body;
}
