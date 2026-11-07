package com.example.webapp.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
/*
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
*/

@SpringBootApplication
@EnableFeignClients
public class WebappApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);

		/*
		// Gera uma SecretKey HS256 (256 bits) automaticamente
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		// Para armazenar em string (ex: application.yml):
		String secretBase64 = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println(secretBase64);
		*/
	}
}