package com.example.ssdassda.controller;

import com.example.ssdassda.dto.DiaryContentsDTO;
import com.example.ssdassda.dto.DiaryDTO;
import com.example.ssdassda.service.DiaryContentsService;
import com.example.ssdassda.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class DiaryContentsController {

    //private DiaryService diaryService;

    private final DiaryContentsService diaryContentsService;
    private final DiaryService diaryService;

//    @GetMapping("/createDiary")
//    public String createDiaryForm(@RequestParam("diaryId") Long diaryId, Model model) {
//        DiaryDTO diary = diaryService.findById(diaryId);
//        if (diary != null) {
//            model.addAttribute("diary", diary);
//            return "createDiary"; // 일기 상세 템플릿
//        } else {
//            model.addAttribute("errorMessage", "해당 ID의 일기장을 찾을 수 없습니다.");
//            return "error"; // 오류 표시 템플릿
//        }
//    }
    @GetMapping("/createDiary")
    public String createDiaryForm(@RequestParam("diaryId") Long diaryId, Model model) {
        System.out.println("Requested diaryId: " + diaryId); // 요청받은 diaryId를 로그로 출력
        DiaryDTO diary = diaryService.findById(diaryId);
        System.out.println("Diary found: " + diary); // 찾은 diary 객체를 로그로 출력

        if (diary != null) {
            model.addAttribute("diary", diary);
            return "createDiary"; // 일기 상세 템플릿
        } else {
            model.addAttribute("errorMessage", "해당 ID의 일기장을 찾을 수 없습니다.");
            return "error"; // 오류 표시 템플릿
        }
    }


//    @GetMapping("/createDiary")
//    public String createDiaryForm(@RequestParam("diaryId") Long diaryId, Model model) {
//        DiaryContentsDTO diary = diaryContentsService.findById(diaryId);
//        if (diary != null) {
//            model.addAttribute("diary", diary);
//            return "createDiary"; // Thymeleaf 템플릿 이름을 반환합니다.
//        } else {
//            return "error"; // 오류 페이지 또는 적절한 페이지로 리다이렉트합니다.
//        }
//    }

    @GetMapping("/diary/createDiary")
    public String saveForm() {
        return "createDiarysave";
    }

    @PostMapping("/diary/createDiary")
    public String save(@ModelAttribute DiaryContentsDTO diaryContentsDTO) throws IOException {
        System.out.println("diaryContentsDTO = " + diaryContentsDTO);
        Long savedDiaryId = diaryContentsService.addDiary(diaryContentsDTO); // 단일 호출
        return "redirect:/createDiary?diaryId=" + savedDiaryId;
    }

    @GetMapping("/diary")
    public ResponseEntity<List<DiaryContentsDTO>> findByDiaryId(@RequestParam("diaryId") Long diaryId) {
        List<DiaryContentsDTO> diaryContentsDTOList = diaryContentsService.findByDiaryId(diaryId);
        return ResponseEntity.ok(diaryContentsDTOList);
    }

    @DeleteMapping("/delete/diaryContents/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") @Valid Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            diaryContentsService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting diary");
        }
    }
}
