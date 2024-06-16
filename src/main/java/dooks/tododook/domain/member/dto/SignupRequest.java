package dooks.tododook.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupRequest {
    @NotEmpty(message = "사용자 이름이 비어있을 수 없습니다.")
    String username;
    @Email(message = "이메일 형식이 아닙니다.")
    @NotEmpty(message = "이메일이 비어있을 수 없습니다.")
    String email;
    @NotEmpty(message = "비밀번호가 비어있을 수 없습니다.")
    String password;
}
