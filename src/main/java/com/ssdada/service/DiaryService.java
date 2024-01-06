package com.ssdada.service;

import com.ssdada.dto.DiaryDto;
import com.ssdada.entity.Diary;
import com.ssdada.repository.BoardRepository;
import com.ssdada.repository.DiaryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final BoardRepository boardRepository;

    public List<Diary> getDiary(Long boardId) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23,59,59);
        Optional<Diary> checkDiary = diaryRepository.findByBoardId(boardId);
        if(checkDiary.isPresent()) {
            Diary diary = new Diary();
            diary.setBoard(checkDiary.get().getBoard());
            List<Diary> diaries = diaryRepository.findByRegDateBetween(startOfDay, endOfDay);
            return diaries;
        } else {
            throw new IllegalStateException("오늘 일기가 없다.");
        }
    }
    public void addDiary(DiaryDto diaryDto) throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse(diaryDto.getRegDate());
        Diary diary = new Diary();
        diary.setDiaryTitle(diaryDto.getDiaryTitle());
        diary.setDiaryContent(diaryDto.getDiaryContent());
        diary.setRegDate(dateTime);


    }
}
