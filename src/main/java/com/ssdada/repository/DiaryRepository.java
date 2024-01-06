package com.ssdada.repository;

import com.ssdada.entity.Diary;
import com.ssdada.entity.DiaryImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByRegDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    Optional<Diary> findByBoardId(Long boardId);
}
