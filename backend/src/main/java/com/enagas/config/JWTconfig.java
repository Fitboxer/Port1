package com.enagas.config;

import com.enagas.model.UserDTO;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;

public class JWTconfig {

    private static final String SECRET_KEY = "a9hXH1VL8tvGXL19YXjeCwLw1Ey7A1MbYFNdUKZQJ1w=";

    public static String generarToken(UserDTO userDTO) {
        return Jwts.builder()
                .setSubject(userDTO.getName())
                .claim("role", userDTO.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(SECRET_KEY))
                .compact();
    }

    public static void validarToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("✅ Token válido");
            System.out.println("👤 Usuario: " + claims.getSubject());
            System.out.println("🕒 Emitido en: " + claims.getIssuedAt());
            System.out.println("⏰ Expira en: " + claims.getExpiration());
            System.out.println("🔐 Rol: " + claims.get("rol", String.class));
        } catch (ExpiredJwtException e) {
            System.out.println("⚠️ Token expirado.");
        } catch (UnsupportedJwtException e) {
            System.out.println("⚠️ Token no soportado.");
        } catch (MalformedJwtException e) {
            System.out.println("⚠️ Token mal formado.");
        } catch (SignatureException e) {
            System.out.println("⚠️ Firma inválida.");
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Token vacío o nulo.");
        } catch (Exception e) {
            System.out.println("⚠️ Token inválido: " + e.getMessage());
        }
    }


}
