package dooks.tododook.domain.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {//todo: refresh token 추가
    String accessToken;

    public static TokenResponse of(String accessToken){
        return new TokenResponse(
                accessToken
        );
    }
}
