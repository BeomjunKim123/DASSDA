package com.example.ssdassda.controller;

import com.example.ssdassda.dto.DiaryDTO;
import com.example.ssdassda.entity.DiaryEntity;
import com.example.ssdassda.service.DiaryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/board/save")
    public String saveForm() {
        return "index";
    }

    @PostMapping("/board/save")
    public ResponseEntity<String> save(@RequestBody @Valid DiaryDTO diaryDTO) { //Requestbody
        try {
            diaryService.save(diaryDTO);
            return ResponseEntity.ok("저장 성공"); // 텍스트 응답
        } catch (Exception e) { //예외
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패");
        }
    }

    @GetMapping("/board")
    public ResponseEntity<List<DiaryDTO>> findAll() {
        List<DiaryDTO> diaryDTOList = diaryService.findAll();
        return ResponseEntity.ok(diaryDTOList); // 리스트를 JSON 형식으로 반환
    }

    @GetMapping("/board/detail/{id}")
    @ResponseBody
    public ResponseEntity<DiaryDTO> findById(@PathVariable("id") Long id) {
        DiaryDTO diaryDTO = diaryService.findById(id);
        if (diaryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diaryDTO);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<DiaryDTO> updateForm(@PathVariable Long id) {
        DiaryDTO diaryDTO = diaryService.findById(id);
        return ResponseEntity.ok(diaryDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<DiaryDTO> update(@RequestBody DiaryDTO diaryDTO) {
        if (diaryDTO.getBoardTitle() == null || diaryDTO.getBoardTitle().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        DiaryDTO updatedDiary = diaryService.update(diaryDTO);
        return ResponseEntity.ok(updatedDiary);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") @Valid Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            diaryService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting diary");
        }
    }


}


//    @GetMapping("/update/{id}")
//    public String updateForm(@PathVariable Long id, Model model){
//        DiaryDTO diaryDTO = diaryService.findById(id);
//        model.addAttribute("boardUpdate", diaryDTO);
//        return "index";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute DiaryDTO diaryDTO, Model model) {
//        DiaryDTO diary = diaryService.update(diaryDTO);
//        model.addAttribute("diary", diary);
//        return "index";
//    }

//    @GetMapping("/createDiary")
//    public String createDiaryForm(@RequestParam("diaryId") Long diaryId, Model model) {
//        DiaryDTO diary = diaryService.findById(diaryId);
//        if (diary != null) {
//            model.addAttribute("diary", diary);
//            return "createDiary"; // Thymeleaf 템플릿 이름을 반환합니다.
//        } else {
//            return "error"; // 오류 페이지 또는 적절한 페이지로 리다이렉트합니다.
//        }
//    }

//    @GetMapping("/board/detail/{id}")
//    public String findById(@PathVariable("id") Long id, Model model){
//        DiaryDTO diaryDTO = diaryService.findById(id);
//        model.addAttribute("board", diaryDTO);
//        return "detail";
//    }

//    @GetMapping("/board")
//    public String findAll(Model model) {
//        List<DiaryDTO> diaryDTOList = diaryService.findAll();
//        model.addAttribute("diaryList", diaryDTOList);
//        return "index";
//
//    }


//    @PostMapping("/board/save")
//    public String save(@ModelAttribute DiaryDTO diaryDTO) {
//        try {
//            System.out.println("diaryDTO = " + diaryDTO);
//            diaryService.save(diaryDTO);
//            return "index";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "errorPage";
//        }
//
//    }
//}