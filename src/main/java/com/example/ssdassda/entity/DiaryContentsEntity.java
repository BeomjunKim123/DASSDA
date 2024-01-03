package com.example.ssdassda.entity;

import com.example.ssdassda.dto.DiaryContentsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "diary_contents_table")
public class DiaryContentsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private DiaryEntity diary;

    @Column(name = "diary_title", length = 100)
    private String diaryTitle;

    @Column(name = "diary_contents", length = 2000)
    private String diaryContents;

    @OneToMany(mappedBy = "diaryContents", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryImgEntity> images;

    @OneToMany(mappedBy = "diaryContents", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikesEntity> likes; // 좋아요 목록 추가

    public static DiaryContentsEntity toSaveEntity(DiaryContentsDTO diaryContentsDTO){
        DiaryContentsEntity diaryContentsEntity = new DiaryContentsEntity();
        diaryContentsEntity.setDiaryTitle(diaryContentsDTO.getDiaryTitle());
        diaryContentsEntity.setDiaryContents(diaryContentsDTO.getDiaryContents());
        return diaryContentsEntity;
    }

}
