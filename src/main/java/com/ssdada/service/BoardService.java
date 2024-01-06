package com.ssdada.service;

import com.ssdada.dto.BoardDto;
import com.ssdada.dto.DiaryDto;
import com.ssdada.entity.*;
import com.ssdada.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class BoardService {
    private final DesignRepository designRepository;
    private final StyleRepository styleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryImgRepository diaryImgRepository;

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    public void insertBoardTitle(BoardDto boardDto, String userId) {
        Member member = memberRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new EntityNotFoundException("멤버 테이블에 없음"));
        Optional<Design> designId = designRepository.findById(Long.valueOf(boardDto.getDesign()));
        Optional<Style> styleId = styleRepository.findById(Long.valueOf(boardDto.getStyle()));
        Board board = new Board();
        board.setTitle(boardDto.getBoardTitle());
        board.setMember(member);
        board.setDesign(designId.get());
        board.setStyle(styleId.get());
        board.setRegDate(LocalDateTime.now());
//        board.setMember(Long.valueOf(userId)); 타입 불일치 엔티티 선언할 때 멤버 타입의 컬럼이기 때문에 멤버에서 찾아와야함..
        boardRepository.save(board);
    }
    public void deleteBoard(Long id) {
        Optional<Board> deleteName = boardRepository.findById(id);
        if(deleteName.isPresent()) {
            boardRepository.delete(deleteName.get());
        } else {
            throw new IllegalStateException("해당 일기장 존재하지 않음");
        }
    }
    public List<Board> getBoard() {
        return boardRepository.findAll();
    }
    public Board updateBoard(BoardDto boardDto) {
        Optional<Board> existingBoard = boardRepository.findById(boardDto.getId());
        if(existingBoard.isPresent()) {
            Board board = existingBoard.get();
            board.setTitle(boardDto.getUpdateTitle());
            boardRepository.save(board);
            return board;
        } else {
            throw new IllegalStateException("해당 일기장 없음");
        }
    }


}
