package com.pard.admlong_be.domain.challenge.controller;

import com.pard.admlong_be.domain.challenge.dto.request.ChallengeRequestDTO;
import com.pard.admlong_be.domain.challenge.service.ChallengeService;
import com.pard.admlong_be.global.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/challenge")
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping("/create")
    @Operation(summary = "입력한 정보의 챌린지를 생성합니다.", description = "보내준 정보의 챌린지를 생성합니다.")
    public ResponseEntity<ResponseDTO> createChallenge(@CookieValue(value = "Authorization") String token, @RequestBody ChallengeRequestDTO.createChallengeRequestDTO request) {
        ResponseDTO responseDTO = challengeService.createChallenge(token, request);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @GetMapping("")
    @Operation(summary = "챌린지 정보 불러오기", description = "challenge_finished 상태를 보내주면 해당 상태의 챌린지들의 정보를 모두 반환해줍니다.")
    public ResponseEntity<ResponseDTO> getChallenge(@RequestParam Boolean challenge_finished) {
        ResponseDTO responseDTO = challengeService.getChallengeByFinished(challenge_finished);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @GetMapping("/{challenge_id}")
    @Operation(summary = "id로 챌린지 정보 불러오기", description = "path variable로 challenge id를 보내주면 해당 아이디의 챌린지 정보를 불러옵니다.")
    public ResponseEntity<ResponseDTO> getChallengeById(@PathVariable Long challenge_id, @CookieValue(value = "Authorization") String token) {
        ResponseDTO responseDTO = challengeService.findChallengeById(challenge_id, token);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @PostMapping("join")
    @Operation(summary = "챌린지 참여하기", description = "토큰과 챌린지 id를 보내주면, 해당 챌린지 id의 챌린지에 사용자를 추가해줍니다.")
    public ResponseEntity<ResponseDTO> joinChallengeById(@CookieValue(value = "Authorization") String token, @RequestParam Long challenge_id) {
        ResponseDTO responseDTO = challengeService.joinChallengeById(token, challenge_id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @GetMapping("/my")
    @Operation(summary = "내가 속한 챌린지 정보 가져오기.", description = "내가 속해있는 모든 챌린지의 상세 정보를 알려줍니다.")
    public ResponseEntity<ResponseDTO> getDetailChallenge(@CookieValue(value = "Authorization") String token) {
        ResponseDTO responseDTO = challengeService.getDetailChallenge(token);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @PostMapping("/like")
    @Operation(summary = "좋아요 누르기/취소하기", description = "이 api를 사용하면 true/false를 반환하는데, 취소했으면 false, 좋아요 눌렀으면 true")
    public ResponseEntity<ResponseDTO> likeChallenge(@CookieValue(value = "Authorization") String token, @RequestParam Long challenge_id) {
        ResponseDTO responseDTO = challengeService.updateChallengeLikeById(token, challenge_id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }
}
