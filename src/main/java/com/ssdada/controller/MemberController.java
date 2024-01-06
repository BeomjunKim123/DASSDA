package com.ssdada.controller;

import com.ssdada.entity.Member;
import com.ssdada.oauth.token.AuthTokensGenerator;
import com.ssdada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberRepository.findAll());
    }
    @GetMapping("/{accessToken}")
    public ResponseEntity<Member> findByAccessToken(@PathVariable String accessToken) {
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        return ResponseEntity.ok(memberRepository.findById(memberId).get());
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateMembers() {
        return null;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMembers() {
        return null;
    }
}
