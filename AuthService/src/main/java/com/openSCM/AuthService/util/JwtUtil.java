package com.openscm.authservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.private-key}")
    private Resource privateKeyResource;

    @Value("${jwt.public-key}")
    private Resource publicKeyResource;

    @Value("${jwt.expiration}")
    private long expiration;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws Exception {
        System.out.println("[DEBUG] Initializing JwtUtil...");
        privateKey = loadPrivateKey(privateKeyResource);
        if (privateKey != null) {
            System.out.println("[DEBUG] Private key loaded successfully.");
        }
        publicKey = loadPublicKey(publicKeyResource);
        if (publicKey != null) {
            System.out.println("[DEBUG] Public key loaded successfully.");
        }
        System.out.println("[DEBUG] JwtUtil initialization completed.");
    }

    private PrivateKey loadPrivateKey(Resource resource) throws Exception {
        if (!resource.exists()) {
            throw new IllegalStateException("Private key file not found in resources!");
        }
        String content = readKey(resource, "PRIVATE");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(content));
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    private PublicKey loadPublicKey(Resource resource) throws Exception {
        if (!resource.exists()) {
            throw new IllegalStateException("Public key file not found in resources!");
        }
        String content = readKey(resource, "PUBLIC");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(content));
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private String readKey(Resource resource, String type) throws IOException {
        String key = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return key.replace("-----BEGIN " + type + " KEY-----", "")
                .replace("-----END " + type + " KEY-----", "")
                .replaceAll("\\s+", "");
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }
}
