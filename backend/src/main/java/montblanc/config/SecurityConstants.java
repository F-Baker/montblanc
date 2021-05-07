package montblanc.config;

public class SecurityConstants {

    public static final String SECRET =
            "bXktc2VjcmV0LWtleSBteS1zZ1NyZXQta2V5IG15LXBlY3JldC1rZXkgbXktc2" +
            "VjcmV0LWtleSBteS1zJWNyZXQta2V5IG15LXNlY3JldC1rHXkgbXktc2VjcmV0LWtl4eSBteS1zZWNyZXQta2V5IG15" +
            "LXNlY3JldC1rEXkgbXktc2VjcmV0LWtleSBteS1zZWNy6ZXQta2V5";

    public static final int EXPIRATION_TIME = 423_000_000;//5 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
