package com.example.testing.utill;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JWTAuthenticator {
    private final String jwtSecret = "your secret key";
    private final int jwtExpirationMs = 86400000;//86400000 = 1 hour ,should use time in seconds

    //generate Token
    public String generateJwtToken(User user) {
        return Jwts.builder()
                // can include own details for this (you can customize)
                .subject((user.getEmail()))
                .id(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        // BASE64 have been use,(you can use any encoder for this)
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    }

    //this method for check validation
    public boolean validateJwtToken(String authToke) {
        String jwtToken = authToke.substring("Bearer ".length());
        try {
            Jwts.parser().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
                System.out.println("Error occurred when validate...");
        }
        return false;

    }
}
//we want to use jwt dependency
/*
<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.12.6</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.12.6</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
			<version>0.12.6</version>
			<scope>runtime</scope>
		</dependency>
 */