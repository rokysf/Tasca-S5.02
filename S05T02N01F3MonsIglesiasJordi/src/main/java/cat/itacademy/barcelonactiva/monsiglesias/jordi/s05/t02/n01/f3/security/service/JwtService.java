package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.service;

import java.security.Key;
import java.util.function.Function;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {

	private static final String SECRET_KEY = "ea649e4efee19477f1fe27dcd20314f2e04082193eabbd4ea69210d23e00578e";

	public String getUserName(String jwt) {
		return getClaim(jwt, Claims::getSubject);
	}

	private <T> T getClaim(String jwt, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(jwt);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaims(String jwt) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.claim("role", userDetails.getAuthorities())
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean validateToken(String jwt, UserDetails userDetails) {
		final String userName = getUserName(jwt);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
	}

	private boolean isTokenExpired(String jwt) {
		return getExpiration(jwt).before(new Date());
	}

	private Date getExpiration(String jwt) {
		return getClaim(jwt, Claims::getExpiration);
	}

}
