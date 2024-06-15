package dooks.tododook.domain.member.entity;

import dooks.tododook.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private Role role;
    private String username;
    private String password;
    private Long kakaoId;

    @Builder
    public Member(Long id, String email, Role role, String username, String password, Long kakaoId) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;
    }
}
