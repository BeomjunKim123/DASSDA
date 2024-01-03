package com.example.ssdassda.service;

import com.example.ssdassda.dto.DiaryDTO;
import com.example.ssdassda.entity.DiaryEntity;
import com.example.ssdassda.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    public void save(DiaryDTO diaryDTO) {
        DiaryEntity diaryEntity = DiaryEntity.tosaveEntity(diaryDTO);
        diaryRepository.save(diaryEntity);
    }

//    public List<DiaryDTO> findAll() {
//        List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
//        List<DiaryDTO> diaryDTOList = new ArrayList<>();
//        for (DiaryEntity diaryEntity: diaryEntityList) {
//            DiaryDTO diaryDTO = DiaryDTO.toDiaryDTO(diaryEntity);
//            diaryDTO.setNumberOfContents(diaryEntity.getNumberOfContents());
//            diaryDTOList.add(DiaryDTO.toDiaryDTO(diaryEntity)); //레파지토리에서 바로 리턴
//
//        }
//        return diaryDTOList;
//
//    }

//    public List<DiaryDTO> findAll() {
//        List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
//        List<DiaryDTO> diaryDTOList = new ArrayList<>();
//        for (DiaryEntity diaryEntity : diaryEntityList) {
//            DiaryDTO diaryDTO = DiaryDTO.toDiaryDTO(diaryEntity);
//            diaryDTO.setNumberOfContents(diaryEntity.getNumberOfContents());
//            diaryDTOList.add(diaryDTO); // 수정: 이미 변환된 diaryDTO를 리스트에 추가
//        }
//        return diaryDTOList;
//    }

    public List<DiaryDTO> findAll() {
        List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        List<DiaryDTO> diaryDTOList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            DiaryDTO diaryDTO = DiaryDTO.toDiaryDTO(diaryEntity);
            int contentsCount = diaryRepository.countContentsByDiaryId(diaryEntity.getId());
            diaryDTO.setNumberOfContents(contentsCount);
            diaryDTOList.add(diaryDTO);
        }
        return diaryDTOList;
    }
//    public DiaryDTO findById(Long id) {
//        Optional<DiaryEntity> optionalDiaryEntity = diaryRepository.findById(id);
//        if (optionalDiaryEntity.isPresent()){
//            DiaryEntity diaryEntity = optionalDiaryEntity.get();
//            DiaryDTO diaryDTO = DiaryDTO.toDiaryDTO(diaryEntity);
//            return diaryDTO;
//        } else {
//            return null;
//        }
//    } //optional 레파지토리로
//    public DiaryDTO findById(Long id) {
//        Optional<DiaryEntity> optionalDiaryEntity = diaryRepository.findById(id);
//        if (optionalDiaryEntity.isPresent()){
//            DiaryEntity diaryEntity = optionalDiaryEntity.get();
////            return DiaryDTO.toDiaryDTO(diaryEntity);
//                return diaryRepository.findById(id)
//                        .map(DiaryDTO::toDiaryDTO)
//                        .orElse(null);
//            } else {
//            // 로그를 추가하여 데이터 조회 실패를 기록합니다.
//            System.out.println("No diary found with ID: " + id);
//            return null;
//        }
//    }

    public DiaryDTO findById(Long id) {
        return diaryRepository.findById(id)
                .map(diaryEntity -> {
                    DiaryDTO diaryDTO = DiaryDTO.toDiaryDTO(diaryEntity);
                    int contentsCount = diaryRepository.countContentsByDiaryId(diaryEntity.getId());
                    diaryDTO.setNumberOfContents(contentsCount);
                    return diaryDTO;
                })
                .orElse(null);
    }

    public DiaryDTO update(DiaryDTO diaryDTO) {
        if (diaryDTO.getBoardTitle() == null || diaryDTO.getBoardTitle().trim().isEmpty()) {
            // 오류 처리 또는 예외 발생
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        DiaryEntity diaryEntity = DiaryEntity.toUpdateEntity(diaryDTO);
        diaryRepository.save(diaryEntity);
        return findById(diaryDTO.getId());
    }

    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }
}
