package dooks.tododook.domain.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dooks.tododook.domain.auth.dto.*;
import dooks.tododook.domain.member.entity.Member;
import dooks.tododook.domain.member.entity.Role;
import dooks.tododook.domain.member.exception.MemberException;
import dooks.tododook.domain.member.repository.MemberRepository;
import dooks.tododook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";


    @Value("${oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${oauth2.client.provider.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${kakao.secret-password}")
    private String kakaoSecretPassword;

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse kakaoLogin(String code) {
        String kakaoAcessToken = getKakaoAccessToken(code);
        KakaoMemberResponse kakaoMemberResponse = getKakaoUserInfo(kakaoAcessToken);
        Member member = null;
        if(memberRepository.existsByKakaoId(kakaoMemberResponse.getId())){
            member = memberRepository.findByKakaoId(kakaoMemberResponse.getId()).orElseThrow(MemberException.MemberNotFoundException::new);
        }else {
            member = Member.builder()
                    .email(kakaoMemberResponse.getId() + "@kakao.com")
                    .password(passwordEncoder.encode(kakaoSecretPassword))
                    .kakaoId(kakaoMemberResponse.getId())
                    .username(kakaoMemberResponse.getNickname())
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }
        memberService.authenticateAndSetSecurityContext(member.getEmail(), kakaoSecretPassword);
        String accessToken = tokenProvider.createToken(member.getEmail(), member.getRole().name());
        return TokenResponse.of(accessToken);
    }

    public String getKakaoAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("client_secret", clientSecret);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(kakaoTokenUri, HttpMethod.POST, request, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.path("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token", e);
        }
    }

    public KakaoMemberResponse getKakaoUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(kakaoUserInfoUri, HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(responseBody);
            Long id = Long.parseLong(jsonNode.path("id").asText());
            String name = jsonNode.path("kakao_account").path("nickname").asText();
            return KakaoMemberResponse.of(id, name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user info", e);
        }
    }
}

