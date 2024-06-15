package dooks.tododook.domain.auth.controller;

import dooks.tododook.domain.auth.dto.TokenResponse;
import dooks.tododook.domain.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService OAuthService;
    @GetMapping("/login/oauth2/callback/kakao")
    public ResponseEntity<TokenResponse> kakaoLogin(@RequestParam String code) {
        return ResponseEntity.ok(OAuthService.kakaoLogin(code));
    }
}
