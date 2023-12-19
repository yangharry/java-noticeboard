package com.example.javanoticeboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.javanoticeboard.model.DeleteStatus;
import com.example.javanoticeboard.model.entity.Board;
import com.example.javanoticeboard.model.request.BoardDeleteRequest;
import com.example.javanoticeboard.model.request.BoardPostRequest;
import com.example.javanoticeboard.model.request.BoardUpdateRequest;
import com.example.javanoticeboard.model.response.BoardListResponse;
import com.example.javanoticeboard.model.response.BoardResponse;
import com.example.javanoticeboard.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse writeBoard(BoardPostRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setBody(request.getBody());
        board.setDeleteStatus(DeleteStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }

    public List<BoardListResponse> searchBoardList(int page, int pageSize) {
        return boardRepository
                .findAllByDeleteStatus(DeleteStatus.ACTIVE,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardNo")))
                .map(BoardListResponse::from).toList();
    }

    public BoardResponse searchBoard(Long boardNo) {
        return boardRepository.findBoardWithCommentsByBoardNo(boardNo).map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
    }

    @Transactional
    public BoardResponse updateBoard(BoardUpdateRequest request) {
        Board board = boardRepository.findById(request.getBoardNo())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        if (board.getDeleteStatus() == DeleteStatus.DELETE) {
            throw new RuntimeException("삭제된 게시글은 수정할 수 없습니다");
        }
        board.setTitle(request.getTitle());
        board.setBody(request.getBody());

        return BoardResponse.from(boardRepository.save(board));

    }

    @Transactional
    public String deleteBoard(BoardDeleteRequest request) {
        Optional<Board> boardOptional = boardRepository.findById(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));

        boardRepository.delete(board);

        return "boardNo :" + " " + request.getBoardNo() + ", " + "deleted successfully";

    }
}
