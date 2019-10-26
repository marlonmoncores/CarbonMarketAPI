package com.hackthon.m100u.CarbonMarketAPI.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Date;
import java.util.List;

public class JWTHelper {

    private JWTHelper(){}

    public static String createToken(JwtConfig jwtConfig, String subject, List<String> authorities){
        Long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                // Convert to list of strings.
                // This is important because it affects the way we get them back in the Gateway.
                .claim("authorities", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
    }

    public static Claims validateAndReadToken(JwtConfig jwtConfig, String jwtTokenOrBearerToken) {
        String jwtToken = jwtTokenOrBearerToken.replace(jwtConfig.getPrefix(), "");

        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
