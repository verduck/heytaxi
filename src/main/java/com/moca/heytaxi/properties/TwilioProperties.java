package com.moca.heytaxi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "twilio")
public class TwilioProperties {
    private final String accountSID;
    private final String apiKey;
    private final String apiSecret;
    private final String verificationServiceSID;
    private final String appHash;
    private final String countryCode;

    public TwilioProperties(String accountSID, String apiKey, String apiSecret, String verificationServiceSID, String appHash, String countryCode) {
        this.accountSID = accountSID;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.verificationServiceSID = verificationServiceSID;
        this.appHash = appHash;
        this.countryCode = countryCode;
    }

    public String getAccountSID() {
        return accountSID;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getVerificationServiceSID() {
        return verificationServiceSID;
    }

    public String getAppHash() {
        return appHash;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
