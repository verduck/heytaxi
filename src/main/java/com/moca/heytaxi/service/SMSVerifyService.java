package com.moca.heytaxi.service;

import com.moca.heytaxi.properties.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSVerifyService {
    private final TwilioProperties twilioProperties;

    @Autowired
    public SMSVerifyService(TwilioProperties twilioProperties) {
        this.twilioProperties = twilioProperties;
        Twilio.init(twilioProperties.getApiKey(), twilioProperties.getApiSecret(), twilioProperties.getAccountSID());
    }

    public String request(String phone) {
        Verification verification = Verification.creator(twilioProperties.getVerificationServiceSID(), phone, "sms")
                .setAppHash(twilioProperties.getAppHash())
                .create();
        return verification.getStatus();
    }
}
