package com.backend.chatop4.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims; // Claims are pieces of information asserted about a subject
//For example, an ID token (which is always a JWT ) can contain a claim called name that asserts that the name of the user authenticating is "John Doe".
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtService {

  private static final String SECRET_KEY = "cmNIYXYyR2pFVGtXZmlXMlBuSDdOMGxsZXNVWlZxeEhwU24raDV6Qkg1VVNPdUJrTlc4ejRXNHo3QkZKZEZFSAo=";
  // TODO mettre en properties et en variable d'environement

  public String extractUserEmail(String jwt) { // for spring UserDetails it is more common to see extractUsername cf:
                                               // isTokenValid
    return extractClaim(jwt, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(Map<String, Objects> extractClaims, UserDetails userDetails) {
    return Jwts
        .builder()
        .setClaims(extractClaims)
        .setSubject(userDetails.getUsername()) // For us is Email but for Spring is always call Username
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userEmail = extractUserEmail(token);
    return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJwt(jwt)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
