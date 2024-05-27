package dooks.tododook.domain.user.service;

import dooks.tododook.domain.user.dto.MemberRequest;
import dooks.tododook.domain.user.entity.Member;
import dooks.tododook.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public Member create(final MemberRequest memberRequest) {
        Member member = Member.builder()
                .email(memberRequest.getEmail())
                .username(memberRequest.getUsername())
                .password(passwordEncoder.encode(memberRequest.getPassword()))
                .build();

        if (member == null || member.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String email = member.getEmail();
        if (memberRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return memberRepository.save(member);
    }

    public Member getByCredentials(final String email, final String password){
        Member member = memberRepository.findByEmail(email).orElse(null); // todo: 예외처리 수정
        if(member == null && passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("User not found");
        }
        return null;
    }
}
