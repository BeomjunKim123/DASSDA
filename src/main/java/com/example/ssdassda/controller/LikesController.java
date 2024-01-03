package com.example.ssdassda.controller;

import com.example.ssdassda.dto.LikesDTO;
import com.example.ssdassda.service.LikesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private LikesService likesService;

//    @PostMapping
//    public ResponseEntity<LikesDTO> likes(@RequestBody @Valid LikesDTO likesDTO) throws Exception {
//        likesService.insert(likesDTO);
//        return new ResponseEntity<>(likesDTO, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<LikesDTO> unlikes(@RequestBody @Valid LikesDTO likesDTO) {
//        likesService.delete(likesDTO);
//        return new ResponseEntity<>(likesDTO, HttpStatus.OK);
//    }


}
