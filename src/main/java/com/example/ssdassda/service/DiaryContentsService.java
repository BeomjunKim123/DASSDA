package com.example.ssdassda.service;

import com.example.ssdassda.dto.DiaryContentsDTO;
import com.example.ssdassda.dto.DiaryDTO;
import com.example.ssdassda.entity.DiaryContentsEntity;
import com.example.ssdassda.entity.DiaryEntity;
import com.example.ssdassda.entity.DiaryImgEntity;
import com.example.ssdassda.repository.DiaryContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryContentsService {

    private final DiaryContentsRepository diaryContentsRepository;

    public DiaryContentsDTO findById(Long id) {
        Optional<DiaryContentsEntity> optionalDiaryContentsEntity = diaryContentsRepository.findById(id);
        if (optionalDiaryContentsEntity.isPresent()) {
            DiaryContentsEntity diaryContentsEntity = optionalDiaryContentsEntity.get();
            DiaryContentsDTO diaryContentsDTO = DiaryContentsDTO.toDiaryContentsDTO(diaryContentsEntity);
            return diaryContentsDTO;
        } else {
            return null;
        }
    }


    //    public DiaryContentsDTO save(DiaryContentsDTO diaryContentsDTO) {
//        DiaryContentsEntity diaryContentsEntity = DiaryContentsEntity.toSaveEntity(diaryContentsDTO);
//        DiaryContentsEntity savedEntity = diaryContentsRepository.save(diaryContentsEntity);
//        return DiaryContentsDTO.toDiaryContentsDTO(savedEntity);
//    }

    @Value("${spring.item-img-location}")
    private String itemImgLocation;

    @Transactional
    public Long addDiary(DiaryContentsDTO diaryContentsDTO) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now(); // 현재 시간을 사용하거나, 필요에 따라 다르게 설정
        DiaryContentsEntity diaryContents = new DiaryContentsEntity();
        diaryContents.setDiaryTitle(diaryContentsDTO.getDiaryTitle());
        diaryContents.setDiaryContents(diaryContentsDTO.getDiaryContents());
        //diaryContents.setBoardCreatedTime(dateTime);

        List<DiaryImgEntity> diaryImgs = new ArrayList<>();
        if (diaryContentsDTO.getDiaryImgs() != null && !diaryContentsDTO.getDiaryImgs().isEmpty()) {
            for (MultipartFile file : diaryContentsDTO.getDiaryImgs()) {
                String originalFilename = file.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "/Users/pizza/Desktop/image/" + storedFileName;

                try {
                    file.transferTo(new File(savePath)); // 파일 저장 시도
                } catch (IOException e) {
                    e.printStackTrace(); // 콘솔에 스택 트레이스를 출력
                    throw e; // 예외를 다시 발생시킴
                }

                DiaryImgEntity diaryImg = new DiaryImgEntity();
                diaryImg.setDiaryImg(storedFileName); // 저장된 파일 이름
                diaryImg.setDiaryContents(diaryContents); // 일기 콘텐츠와 연결
                diaryImgs.add(diaryImg);
            }
        }

        try {
            diaryContents.setImages(diaryImgs);
            DiaryContentsEntity savedEntity = diaryContentsRepository.save(diaryContents);
            return savedEntity.getId();
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 스택 트레이스를 출력
            throw e; // 예외를 다시 발생시킴
        }
    }


    public List<DiaryContentsDTO> findByDiaryId(Long diaryId) {
        List<DiaryContentsEntity> diaryContentsEntityList = diaryContentsRepository.findAll();
        List<DiaryContentsDTO> diaryContentsDTOList = new ArrayList<>();
        for (DiaryContentsEntity diaryContentsEntity: diaryContentsEntityList) {
            diaryContentsDTOList.add(DiaryContentsDTO.toDiaryContentsDTO(diaryContentsEntity)); //레파지토리에서 바로 리턴
        }
        return diaryContentsDTOList;

    }

    public void delete(Long id) {
        diaryContentsRepository.deleteById(id);
    }
}
