package dooks.tododook.domain.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {
    Long id;
    String email;
    String accessToken;

    public static TokenResponse of(Long id, String email, String accessToken){
        return new TokenResponse(
                id,
                email,
                accessToken
        );
    }
}
