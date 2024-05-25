package dooks.tododook.domain.user.dto;

import dooks.tododook.domain.user.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
    Long id;
    String email;
    String username;
    String password;

    public static MemberResponse of(Member member){
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getUsername(),
                member.getPassword()
        );
    }
}
