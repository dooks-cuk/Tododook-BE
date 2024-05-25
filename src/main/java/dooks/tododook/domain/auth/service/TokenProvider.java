package dooks.tododook.domain.auth.service;

import dooks.tododook.domain.user.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    private static final String SECRET_KEY = "NMA8JPctFuna59f5";

    public String create(Member member){
        Date expprieDate = Date.from(
                Instant.now()
                        .plus(1,ChronoUnit.DAYS));

                return Jwts.builder()
                        .signWith(SignatureAlgorithm.ES512, SECRET_KEY)
                        .setSubject(member.getId().toString()) //Todo: 수정 예정
                        .setIssuer("todo app")
                        .setIssuedAt(new Date())
                        .setExpiration(expprieDate)
                        .compact();
    }

    public String validateAndGetUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
