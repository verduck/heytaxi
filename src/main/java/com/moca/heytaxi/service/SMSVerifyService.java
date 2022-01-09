package com.moca.heytaxi.service;

import com.moca.heytaxi.dto.SMSVerifyDTO;
import com.moca.heytaxi.properties.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
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

    public String request(SMSVerifyDTO smsVerifyDTO) {
        Verification verification = Verification.creator(twilioProperties.getVerificationServiceSID(), smsVerifyDTO.getPhone(), "sms")
/*                .setAppHash(twilioProperties.getAppHash())*/
                .create();
        return verification.getStatus();
    }

    public String verify(SMSVerifyDTO smsVerifyDTO) {
        VerificationCheck verificationCheck = VerificationCheck.creator(twilioProperties.getVerificationServiceSID(), smsVerifyDTO.getMessage())
                .setTo(smsVerifyDTO.getPhone())
                .create();

        return verificationCheck.getStatus();
    }
}
