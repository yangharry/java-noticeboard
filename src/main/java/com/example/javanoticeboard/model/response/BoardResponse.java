package com.example.javanoticeboard.model.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.javanoticeboard.model.DeleteStatus;
import com.example.javanoticeboard.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardResponse {
    private Long boardNo;
    private String title;
    private String body;
    private DeleteStatus deleteStatus;
    private List<CommentResponse> commnets;

    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getBody(),
                board.getDeleteStatus(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())

        );
    }
}
