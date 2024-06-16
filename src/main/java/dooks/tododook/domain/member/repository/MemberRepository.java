package dooks.tododook.domain.member.repository;

import dooks.tododook.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
    boolean existsByKakaoId(Long kakoId);
    Optional<Member> findByKakaoId(Long kakoId);
}
