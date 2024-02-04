package com.nihat.flightsearchapi.security.jwt;

import com.nihat.flightsearchapi.entities.AppUser;
import com.nihat.flightsearchapi.entities.Role;
import com.nihat.flightsearchapi.entities.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String secretKey = "amadeusCaseStudySecretKey1ASDasdXCVxcvqwerQWERtactivityTrackerSecretKey1ASDasdXCVxcvqwerQWERtactivityTrackerSecretKey1ASDasdXCVxcvqwerQWERt"; // Change this with a strong secret key
    private final long validityInMilliseconds = 36000000; // 10 hours

    public JwtTokenProvider() {

    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String createToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("auth", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        Set<UserRole> roles = new HashSet<>();

        authorities.stream()
                .forEach(auth -> roles.add(UserRole.builder().role(getRole(auth.getAuthority())).build()));

        UserDetails userDetails = AppUser.builder()
                .username(claims.getSubject())
                .userRoles(roles)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }


    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Role getRole(String auth) {
        return Role.ADMIN.name().equals(auth) ?
                Role.ADMIN : Role.USER;
    }

}
