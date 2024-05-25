package dooks.tododook.domain.auth.controller;

import dooks.tododook.domain.auth.dto.TokenResponse;
import dooks.tododook.domain.auth.service.TokenProvider;
import dooks.tododook.domain.user.dto.MemberRequest;
import dooks.tododook.domain.user.dto.MemberResponse;
import dooks.tododook.domain.user.entity.Member;
import dooks.tododook.domain.user.service.MemberService;
import dooks.tododook.global.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody final MemberRequest memberRequest){
        try {
            Member registeredMember = memberService.create(memberRequest);
            MemberResponse memberResponse = MemberResponse.of(registeredMember);
            return ResponseEntity.ok().body(memberResponse);
        }
        catch (Exception e){
            ErrorResponse errorResponse = ErrorResponse.builder()
                            .error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody final MemberRequest memberRequest) {
        Member member = memberService.getByCredentials(memberRequest.getEmail(), memberRequest.getPassword());
        if(member != null){
            String token = tokenProvider.create(member);
            final TokenResponse tokenResponse = TokenResponse.of( member.getId(), member.getEmail(), token);
            return ResponseEntity.ok().body(tokenResponse);
        }else{
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .error("Login failed").build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
