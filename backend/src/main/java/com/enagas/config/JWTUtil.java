package com.enagas.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Base64;

public class JWTUtil {

    private static final String SECRET_KEY = "a9hXH1VL8tvGXL19YXjeCwLw1Ey7A1MbYFNdUKZQJ1w=";

    public static String obtenerRol(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();

        return claims.get("rol", String.class);
    }

    public static boolean tieneRol(String token, String rolRequerido) {
        String rol = obtenerRol(token);
        return rol != null && rol.equals(rolRequerido);
    }
}
