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
@RequestMapping("/api")
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping("/challenge/create")
    @Operation(summary = "입력한 정보의 챌린지를 생성합니다.", description = "보내준 정보의 챌린지를 생성합니다.")
    public ResponseEntity<ResponseDTO> createChallenge(@RequestBody ChallengeRequestDTO.createChallengeRequestDTO request) {
        ResponseDTO responseDTO = challengeService.createChallenge(request);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @GetMapping("/challenge")
    @Operation(summary = "챌린지 정보 불러오기", description = "challenge_finished 상태를 보내주면 해당 상태의 챌린지들의 정보를 모두 반환해줍니다.")
    public ResponseEntity<ResponseDTO> getChallenge(@RequestParam Boolean challenge_finished){
        ResponseDTO responseDTO = challengeService.getChallengeByFinished(challenge_finished);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }

    @GetMapping("/challenge/{challenge_id}")
    @Operation(summary = "id로 챌린지 정보 불러오기", description = "path variable로 challenge id를 보내주면 해당 아이디의 챌린지 정보를 불러옵니다.")
    public ResponseEntity<ResponseDTO> getChallengeById(@PathVariable Long challenge_id){
        ResponseDTO responseDTO = challengeService.findChallengeById(challenge_id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(responseDTO);
    }
}
