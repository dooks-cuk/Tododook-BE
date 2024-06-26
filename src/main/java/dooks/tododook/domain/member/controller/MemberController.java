package dooks.tododook.domain.member.controller;

import dooks.tododook.domain.auth.dto.TokenResponse;
import dooks.tododook.domain.member.dto.LoginRequest;
import dooks.tododook.domain.member.dto.SignupRequest;
import dooks.tododook.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registerMember(@RequestBody @Valid final SignupRequest request){
        memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> Login(@RequestBody final LoginRequest request){
        return ResponseEntity.ok().body(memberService.login(request));
    }
}
