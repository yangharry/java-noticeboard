package com.example.javanoticeboard.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.javanoticeboard.model.DeleteStatus;
import com.example.javanoticeboard.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments c WHERE b.boardNo = :boardNo AND b.deleteStatus = 'ACTIVE' AND (c.deleteStatus = 'ACTIVE' OR c IS NULL)")
    Optional<Board> findBoardWithCommentsByBoardNo(@Param("boardNo") Long boardNo);
}
