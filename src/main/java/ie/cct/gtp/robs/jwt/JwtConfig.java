package ie.cct.gtp.robs.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@ConfigurationProperties(prefix = "application.jwt")
@Configuration
public class JwtConfig {

//	private String secretKey;
//	private String tokenPrefix;
//	private Integer tokenExpirationAfterDays;

	@Value("${application.jwt.secretKey}")
	private String secretKey;
	@Value("${application.jwt.tokenPrefix}")
	private String tokenPrefix;
	@Value("${application.jwt.tokenExpirationMillis}")
	private Integer tokenExpirationMillis;

	public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationMillis() {
        return tokenExpirationMillis;
    }

    public void setTokenExpirationMillis(Integer tokenExpirationMillis) {
        this.tokenExpirationMillis = tokenExpirationMillis;
    }

}
