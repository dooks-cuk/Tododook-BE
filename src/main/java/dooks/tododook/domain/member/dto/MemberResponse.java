package dooks.tododook.domain.member.dto;

import dooks.tododook.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
    Long id;
    String username;
    String email;
    String password;

    public static MemberResponse of(Member member){
        return new MemberResponse(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getPassword()
        );
    }
}
