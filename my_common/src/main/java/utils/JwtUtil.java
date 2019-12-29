package utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String signKey;
    private long expireDuring;

    public String createToken(String id, String userName, String phone, String roleTypeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("phone", phone);
        map.put("roleType", roleTypeId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setClaims(map)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, signKey);
        if (expireDuring > 0) {
            jwtBuilder.setExpiration(new Date(new Date().getTime() + expireDuring));
        }
        return jwtBuilder.compact();
    }

    public Claims parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
