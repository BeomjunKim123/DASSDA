package com.ssdada.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class DiaryDto {
    private Long id;
    private Long boardId;
    private Long stickerId;
    private String diaryTitle;
    private String diaryContent;
    private String regDate;
    private String updateDate;
    private List<MultipartFile> diaryImgs;
}
