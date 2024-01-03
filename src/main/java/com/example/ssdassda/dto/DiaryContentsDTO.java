package com.example.ssdassda.dto;

import com.example.ssdassda.entity.DiaryContentsEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class DiaryContentsDTO {

    private Long id;
    private String diaryTitle;
    private String diaryContents;
    private LocalDateTime boardCreatedTime;
    private List<MultipartFile> diaryImgs = new ArrayList<>();



    public static DiaryContentsDTO toDiaryContentsDTO(DiaryContentsEntity diaryContentsEntity) {
        DiaryContentsDTO dto = new DiaryContentsDTO();
        dto.setId(diaryContentsEntity.getId());
        dto.setDiaryTitle(diaryContentsEntity.getDiaryTitle());
        dto.setDiaryContents(diaryContentsEntity.getDiaryContents());
        // 기타 필요한 필드 설정
        return dto;
    }
}
