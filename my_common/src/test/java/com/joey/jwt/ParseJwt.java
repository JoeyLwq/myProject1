package com.joey.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

public class ParseJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("joey")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJqb2V5IiwiaWF0IjoxNTc3NjE5MDMyLCJleHAiOjE1Nzc2MTkwOTJ9.2FtEuIFGHoIQKIezL8EtdYvK2UXLqgFcgZWY9Y3AiYg")
                .getBody();
        System.out.println(claims.toString());
    }
}
