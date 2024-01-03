package com.example.ssdassda.dto;

import com.example.ssdassda.entity.DiaryEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryDTO {

    private Long id;

    @Size(max = 10, message = "제목은 최대 10글자까지만 입력 가능합니다.")
    private String boardTitle;
    //private String boardContents;
    private LocalDateTime boardCreatedTime;
    private int numberOfContents; // 일기 개수 필드 추가

    public static DiaryDTO toDiaryDTO(DiaryEntity diaryEntity) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setId(diaryEntity.getId());
        diaryDTO.setBoardTitle(diaryEntity.getBoardTitle());
        diaryDTO.setBoardCreatedTime(diaryEntity.getCreatedTime());
        diaryDTO.setNumberOfContents(diaryEntity.getNumberOfContents());
        return diaryDTO;
    }
}
