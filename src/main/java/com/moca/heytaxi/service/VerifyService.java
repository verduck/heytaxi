package com.moca.heytaxi.service;

import com.moca.heytaxi.dto.TokenDTO;
import com.moca.heytaxi.dto.VerifyDTO;
import com.moca.heytaxi.properties.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyService {
    private final TwilioProperties twilioProperties;

    @Autowired
    public VerifyService(TwilioProperties twilioProperties) {
        this.twilioProperties = twilioProperties;
        Twilio.init(twilioProperties.getApiKey(), twilioProperties.getApiSecret(), twilioProperties.getAccountSID());
    }

    public VerifyDTO.Response request(VerifyDTO.Request request) {
        VerifyDTO.Response response = new VerifyDTO.Response();
        try {
            Verification verification = Verification.creator(twilioProperties.getVerificationServiceSID(), request.getPhone(), "sms")
/*                .setAppHash(twilioProperties.getAppHash())*/
                    .setLocale("ko")
                    .create();
            response.setResult(true);
            response.setMessage(verification.getStatus());
        } catch (TwilioException e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public TokenDTO verify(VerifyDTO.Request request) {
        TokenDTO response = new TokenDTO();
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
