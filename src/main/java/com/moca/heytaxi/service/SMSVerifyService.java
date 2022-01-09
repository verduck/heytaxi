package com.moca.heytaxi.service;

import com.moca.heytaxi.dto.SMSVerifyDTO;
import com.moca.heytaxi.properties.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
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

    public SMSVerifyDTO.Response request(SMSVerifyDTO.Request request) {
        SMSVerifyDTO.Response response = new SMSVerifyDTO.Response();
        try {
            Verification verification = Verification.creator(twilioProperties.getVerificationServiceSID(), request.getPhone(), "sms")
/*                .setAppHash(twilioProperties.getAppHash())*/
                    .create();
            response.setResult(true);
            response.setMessage(verification.getStatus());
        } catch (TwilioException e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public SMSVerifyDTO.Response verify(SMSVerifyDTO.Request request) {
        SMSVerifyDTO.Response response = new SMSVerifyDTO.Response();
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(twilioProperties.getVerificationServiceSID(), request.getCode())
                    .setTo(request.getPhone())
                    .create();
            response.setResult(true);
            response.setMessage(verificationCheck.getStatus());
        } catch (TwilioException e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
