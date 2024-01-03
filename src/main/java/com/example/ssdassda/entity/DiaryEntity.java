package com.example.ssdassda.entity;

import com.example.ssdassda.dto.DiaryDTO;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "diary_table")
public class DiaryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String boardTitle;

//    @Column
//    private String boardContents;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DiaryContentsEntity> contents = new ArrayList<>();


    public static DiaryEntity tosaveEntity(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = new DiaryEntity();
        //diaryEntity.setId(diaryDTO.getId());
        diaryEntity.setBoardTitle(diaryDTO.getBoardTitle());
        //diaryEntity.setBoardContents(diaryDTO.getBoardContents());
        return diaryEntity;
    }

    public static DiaryEntity toUpdateEntity(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setId(diaryDTO.getId());
        diaryEntity.setBoardTitle(diaryDTO.getBoardTitle());
        //diaryEntity.setBoardContents(diaryDTO.getBoardContents());
        return diaryEntity;
    }

    // 일기 개수를 반환하는 메소드 추가
    @Transient // 이 어노테이션은 JPA가 이 필드를 데이터베이스 컬럼으로 매핑하지 않도록 합니다.
    public int getNumberOfContents() {
        return contents != null ? contents.size() : 0;
    }
}
