package com.ssdada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recomment")
public class RecommentController {

    @PostMapping("/add")
    public ResponseEntity<Void> addRecomment() {
        return null;
    }
    @GetMapping("/get")
    public ResponseEntity<Void> getRecomment() {
        return null;
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateRecomment() {
        return null;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecomment() {
        return null;
    }
}
