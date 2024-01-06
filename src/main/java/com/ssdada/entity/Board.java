package com.ssdada.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "design_id", referencedColumnName = "id")
    private Design design;

    @OneToOne
    @JoinColumn(name = "style_id", referencedColumnName = "id")
    private Style style;

    @Column(name = "board_title")
    private String title;

    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
