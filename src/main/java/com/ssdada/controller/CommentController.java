package com.ssdada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    @PostMapping("/add")
    public ResponseEntity<Void> addComment() {
        return null;
    }
    @GetMapping("/get")
    public ResponseEntity<Void> getComment() {
        return null;
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateComment() {
        return null;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCommennt() {
        return null;
    }
}
