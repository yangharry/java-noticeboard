package com.example.javanoticeboard.model.entity;

import org.hibernate.annotations.SQLDelete;

import com.example.javanoticeboard.model.DeleteStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE comment SET DELETE_STATUS = 'DELETE' WHERE comment_no = ?")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @Column(length = 1000)
    private String body;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_NO")
    private Board board;
}
