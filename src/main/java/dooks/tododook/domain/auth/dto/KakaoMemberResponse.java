package dooks.tododook.domain.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoMemberResponse {
    private Long id;
    private String nickname;

    public static KakaoMemberResponse of(Long id, String nickname){
        return new KakaoMemberResponse(id, nickname);
    }
}
