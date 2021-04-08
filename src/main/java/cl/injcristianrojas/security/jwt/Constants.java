package cl.injcristianrojas.security.jwt;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
	
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/login";

}
