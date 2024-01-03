package com.example.ssdassda.repository;

import com.example.ssdassda.entity.LikesEntity;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

//    Optional<LikesEntity> findByUserIdAndDiaryContentsId(Long userId, Long diaryContentsId);


}
