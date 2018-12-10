package cl.injcristianrojas.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;

public class Constants {
	
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME_IN_MILLISECONDS = 864_000_000; // milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/login";
    
    public static Algorithm verificationAlgorithm() {
    	return Algorithm.HMAC256(SECRET);
    }

}
