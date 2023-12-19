package com.example.javanoticeboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.javanoticeboard.model.request.BoardDeleteRequest;
import com.example.javanoticeboard.model.request.BoardPostRequest;
import com.example.javanoticeboard.model.request.BoardUpdateRequest;
import com.example.javanoticeboard.model.response.BoardListResponse;
import com.example.javanoticeboard.model.response.BoardResponse;
import com.example.javanoticeboard.service.BoardService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 등록
    @PostMapping("board")
    public BoardResponse writeBoard(@RequestBody BoardPostRequest boardPostRequst) {
        return boardService.writeBoard(boardPostRequst);
    }

    // 게시글 목록 조회
    @GetMapping("boards")
    public List<BoardListResponse> searchBoardList(@RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {
        return boardService.searchBoardList(page, pageSize);
    }

    // 게시글 조회
    @GetMapping("board")
    public BoardResponse searchBoard(@RequestParam("boardNo") Long boardNo) {
        return boardService.searchBoard(boardNo);
    }

    // 게시글 수정
    @PutMapping("board")
    public BoardResponse updateBoard(@RequestBody BoardUpdateRequest boardUpdateRequest) {
        return boardService.updateBoard(boardUpdateRequest);
    }

    // 게시글 삭제
    @DeleteMapping("board")
    public String deleteBoard(@RequestBody BoardDeleteRequest boardDeleteRequest) {
        return boardService.deleteBoard(boardDeleteRequest);
    }
}
