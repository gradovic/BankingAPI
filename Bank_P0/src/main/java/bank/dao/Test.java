package bank.dao;

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

public class Test {

	public static void main(String[] args) {
		JwtManager jwtManager = new JwtManager();
		String tokenAdmin = jwtManager.createToken("test", "admin");
		String tokenUser = jwtManager.createToken("test", "user");
		System.out.println(tokenAdmin);
		/*
		 * try { Thread.sleep(25000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println(tokenUser);
		Jws<Claims> resultAdmin = jwtManager.parseToken(tokenAdmin);
		Jws<Claims> resultUser = jwtManager.parseToken(tokenUser);
	
		System.out.println(resultAdmin);
		System.out.println(resultUser);

		
		
	}

}

class JwtManager {
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	private static final SecretKey SECRET_KEY = MacProvider.generateKey( SIGNATURE_ALGORITHM );
	private static final TemporalAmount TOKEN_VALIDITY = Duration.ofHours(4L);
	private static final String CLAIM_ROLE = "role";
	
	public String createToken( final String subject, final String role ) {
        final Instant now = Instant.now();
        final Date expiryDate = Date.from( now.plus( TOKEN_VALIDITY ) );
        return Jwts.builder()
                   .setSubject( subject )
                   .claim( CLAIM_ROLE, role )
                   .setExpiration( expiryDate )
                   .setIssuedAt( Date.from( now ) )
                   .signWith( SIGNATURE_ALGORITHM, SECRET_KEY )
                   .compact();
    }
	
	public Jws<Claims> parseToken( final String compactToken )
            throws ExpiredJwtException,
                   UnsupportedJwtException,
                   MalformedJwtException,
                   SignatureException,
                   IllegalArgumentException {
        return Jwts.parser()
                   .setSigningKey( SECRET_KEY )
                   .parseClaimsJws( compactToken );
    }
}
