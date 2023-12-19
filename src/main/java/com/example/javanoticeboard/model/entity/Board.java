package com.example.javanoticeboard.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;

import com.example.javanoticeboard.model.DeleteStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_no = ?")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;
    private String title;

    @Column(length = 1000)
    private String body;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // board 삭제할 때 관련 댓글 삭제
    private List<Comment> comments = new ArrayList<>();

    public Board addComment(String commentBody) {
        Comment comment = new Comment();
        comment.setBody(commentBody);
        comment.setBoard(this);
        comment.setDeleteStatus(DeleteStatus.ACTIVE);

        this.getComments().add(comment);
        return this;
    }
}
