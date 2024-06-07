package dooks.tododook.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    Long id;
    String email;
    String username;
    String password;
}
