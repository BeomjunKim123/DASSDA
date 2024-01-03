package com.example.ssdassda.repository;

import com.example.ssdassda.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    @Query("SELECT COUNT(c) FROM DiaryContentsEntity c WHERE c.diary.id = :diaryId")
    int countContentsByDiaryId(@Param("diaryId") Long diaryId);




}
