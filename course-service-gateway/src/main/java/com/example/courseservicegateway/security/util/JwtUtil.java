package com.example.courseservicegateway.security.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ACCESS TOKEN
    private final String ACCESS_TOKEN = "c03517205b576a0ce0ef82a90cace480756711001f5a095387fae122529c75194d1d644de1b102e3d9ef1e993b5b3dfb369b7c1a44b363c799629a7c4b47c3202d1ea7f6ec2e2dd6c709cd59dbe504a1b83c03229b63ee143d6ee5c67a591aba84064dae9ba4e7cf7d603c8cb31c3ff217cadeb603cf64f5f9bc582fd35ac5956d4c3413bfac86de76e91ed718e6656d7aff62e004d669043f66e606b307fe0e7284a97a349f7baf3b7f94e029efe646d6da9b1efed3ae71a7c86a33688efb170387f509725c0414aa814ed941b2bf4b3ee88c38e8b3c72e04214df75b7cabad4e420b2ac81d247f8e356f10dc711110de807f49993c5c047c4230c22acf5880";

    private final Key key = Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes());
    private final long expirationMs = 86400000; // 1 day

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false; // token invalid or expired
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}

