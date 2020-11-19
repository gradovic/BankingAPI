package bank.JWT;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtManager {

	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	private static final SecretKey SECRET_KEY = MacProvider.generateKey(SIGNATURE_ALGORITHM);
	private static final TemporalAmount TOKEN_VALIDITY = Duration.ofHours(4L);
	
	public static String createToken(final String id, final String role) {
		final Instant now = Instant.now();
		final Date expiryDate = Date.from(now.plus(TOKEN_VALIDITY));
		return Jwts.builder().claim("uid", id).claim("role", role).setExpiration(expiryDate)
				.setIssuedAt(Date.from(now)).signWith(SIGNATURE_ALGORITHM, SECRET_KEY).compact();
	}

	public static Jws<Claims> parseToken(final String compactToken) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		// remove Bearer string from compactToken
		String JwtToken = compactToken.split(" ")[1];
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(JwtToken);
	}

}
