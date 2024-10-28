package com.example.testing;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class TestingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void TestMe() {
		SecretKey key = Jwts.SIG.HS256.key().build();
		byte[] encoded = key.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(encoded);
		System.out.println(encodedKey);
	}
}
