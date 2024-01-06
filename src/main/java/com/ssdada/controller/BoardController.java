package com.ssdada.controller;

import com.ssdada.dto.BoardDto;
import com.ssdada.dto.DiaryDto;
import com.ssdada.dto.LoginDto;
import com.ssdada.entity.Board;
import com.ssdada.entity.Diary;
import com.ssdada.oauth.jwt.JwtTokenProvider;
import com.ssdada.oauth.token.AuthTokens;
import com.ssdada.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@CrossOrigin("https://editor.swagger.io")
public class BoardController {
    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/add")
    public ResponseEntity<Void> makeBoardTitle(@RequestHeader(value = "Authorization") String authorization, @RequestBody BoardDto boardDto) {
        String token = authorization.substring(7);
        String userId = jwtTokenProvider.extractSubject(token);
        System.out.println(boardDto.getBoardTitle());
        System.out.println(boardDto.getDesign());
        System.out.println(boardDto.getStyle());
        boardService.insertBoardTitle(boardDto, userId);
        return ResponseEntity.ok().build();
    }
    //액세스 토큰 미들웨어 두고
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public ResponseEntity<List<Board>> getBoard() {
        List<Board> boards = boardService.getBoard();
        return ResponseEntity.ok(boards);
    }
    @PostMapping("/update")
    public ResponseEntity<Board> updateBoard(@RequestBody BoardDto boardDto) {
        Board updateBoard = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(updateBoard);
    }
    @GetMapping("/main/{accessToken}")

    public String initMain(@PathVariable String accessToken, Model model) {
        model.addAttribute("token", accessToken);
        return "/index";
    }
    @GetMapping("/count")
    public ResponseEntity<Void> allCountBoard() {
        return null;
    }
    @GetMapping("/members/count")
    public ResponseEntity<Void> membersCountBoard() {
        return null;
    }
    @GetMapping("/add/badge")
    public ResponseEntity<Void> newBadgeBoard() {
        return null;
    }

}
