package com.openscm.authservice.controller;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.Base64;

@RestController
public class JwksController {

    @Value("${jwt.public-key}")
    private Resource publicKeyResource;

    private RSAKey rsaJwk;

    @PostConstruct
    public void init() throws Exception {
        String publicKeyContent = new String(publicKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);

        rsaJwk = new RSAKey.Builder(publicKey)
                .keyID("authservice-keys")
                .build();
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        return Map.of("keys", java.util.List.of(rsaJwk.toPublicJWK().toJSONObject()));
    }
}
