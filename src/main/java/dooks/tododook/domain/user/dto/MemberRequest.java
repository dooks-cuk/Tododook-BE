package dooks.tododook.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {
    Long id;
    String email;
    String username;
    String password;
}
