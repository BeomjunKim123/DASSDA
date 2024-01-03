package com.example.ssdassda.entity;

import com.example.ssdassda.dto.DiaryContentsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "diary_img")
public class DiaryImgEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diary_contents_id", referencedColumnName = "id")
    private DiaryContentsEntity diaryContents;

    @Column(name = "diary_img", nullable = true)
    private String diaryImg;


}