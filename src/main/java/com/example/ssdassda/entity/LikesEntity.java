package com.example.ssdassda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@Entity
public class LikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diary_contents_id")
    private DiaryContentsEntity diaryContents;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user; (카카오 Oauth 테스트 유저있어야함)
}
