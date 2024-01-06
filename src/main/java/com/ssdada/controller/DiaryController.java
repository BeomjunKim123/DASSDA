package com.ssdada.controller;

import com.ssdada.dto.DiaryDto;
import com.ssdada.entity.Diary;
import com.ssdada.service.DiaryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
@CrossOrigin("https://editor.swagger.io")
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping("/add")
    public ResponseEntity<Diary> addDiary(@ModelAttribute DiaryDto diaryDto) throws Exception {
        diaryService.addDiary(diaryDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public ResponseEntity<List<Diary>> getDiary(@RequestParam Long boardId) {
        System.out.println(boardId);
        List<Diary> diaries = diaryService.getDiary(boardId);
        return ResponseEntity.ok(diaries);
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateDiary() {
        return null;
    }
    @GetMapping("/delete")
    public ResponseEntity<Void> deleteDiary() {
        return null;
    }
    @GetMapping("/count")
    public ResponseEntity<Void> countDiary() {
        return null;
    }
    @PostMapping("/likes")
    public ResponseEntity<Void> updateLikes() {
        return null;
    }
    @GetMapping("/likes/count")
    public ResponseEntity<Void> countLikes() {
        return null;
    }
    @GetMapping("/comment/count")
    public ResponseEntity<Void> countComment() {
        return null;
    }
    @GetMapping("/algorithm/emoji")
    public ResponseEntity<Void> emoji() {
        return null;
    }
}
