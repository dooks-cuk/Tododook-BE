package dooks.tododook.domain.member.service;

import dooks.tododook.domain.auth.dto.TokenResponse;
import dooks.tododook.domain.auth.service.TokenProvider;
import dooks.tododook.domain.member.dto.LoginRequest;
import dooks.tododook.domain.member.dto.SignupRequest;
import dooks.tododook.domain.member.entity.Member;
import dooks.tododook.domain.member.entity.Role;
import dooks.tododook.domain.member.exception.MemberException;
import dooks.tododook.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signup(final SignupRequest signupRequest) {
        validateAlreadyExistEmail(signupRequest.getEmail());

        Member member = Member.builder()
                .email(signupRequest.getEmail())
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public TokenResponse login(final LoginRequest loginRequest){
        Member member = validateEmailAndPasswordMatch(loginRequest.getEmail(), loginRequest.getPassword());

        authenticateAndSetSecurityContext(loginRequest.getEmail(), loginRequest.getPassword());

        String accessToken = tokenProvider.createToken(member.getEmail(), member.getRole().name());
        return TokenResponse.of(accessToken);
    }

    public void validateAlreadyExistEmail(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException.EmailAlreadyExistException();
        }
    }

    public Member validateEmailAndPasswordMatch(final String email, final String password){
        return memberRepository.findByEmail(email)
                .filter(it -> passwordEncoder.matches(password, it.getPassword()))
                .orElseThrow(MemberException.EmailOrPasswordNotMatch::new);
    }
    @Transactional
    public void authenticateAndSetSecurityContext(final String email, final String password) {
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
