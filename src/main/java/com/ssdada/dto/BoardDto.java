package com.ssdada.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private Long memberId;
    private String boardTitle;
    private String updateTitle;
    private String design;
    private String style;
    private String share;
}
