package com.project.Util;

import com.project.Entity.StoredUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;

public class TokenUtils {

    public static String generateToken(StoredUser user) {
        String token = Jwts.builder()
                .claim("valid", true)
                .claim("username", user.getUsername())
                .signWith(
                        SignatureAlgorithm.HS256,
                        "secretkey".getBytes(StandardCharsets.UTF_8)
                )
                .compact();
        return token;
    }

    public static String getClaimFromToken(String token, String key) {

        return Jwts.parser()
                .setSigningKey("secretkey".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .get(key)
                .toString();
    }
}
