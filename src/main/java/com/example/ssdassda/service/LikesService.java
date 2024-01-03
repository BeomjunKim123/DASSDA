package com.example.ssdassda.service;

import com.example.ssdassda.dto.LikesDTO;
import com.example.ssdassda.entity.DiaryContentsEntity;
import com.example.ssdassda.repository.DiaryContentsRepository;
import com.example.ssdassda.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikesService {

    private final DiaryRepository diaryRepository;
    private final DiaryContentsRepository diaryContentsRepository;

//    public void insert(LikesDTO likesDTO) throws Exception {
//
//        DiaryContentsEntity diaryContents = DiaryContentsRepository.findById(LikesDTO.)
//    }
//
//    public void delete(LikesDTO likesDTO) {ㅠ
//    }


}
