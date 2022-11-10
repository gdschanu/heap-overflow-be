package hanu.gdsc.domain.systemAdminAuth.models;

import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.exceptions.ExpiredCodeException;

import java.util.Random;

public class ForgotPasswordCode {
    private Id systemAdminId;
    private String code;
    private DateTime expireAt;
    
    private ForgotPasswordCode(Id systemAdminId, String code, DateTime expireAt) {
        this.systemAdminId = systemAdminId;
        this.code = code;
        this.expireAt = expireAt;
    }

    public static ForgotPasswordCode createForgotPasswordCode(Id systemAdminId) {
        return new ForgotPasswordCode(
            systemAdminId,
            generateRandomCode(), 
            DateTime.now().plusMinutes(20));
    }
    
    public Id getSystemAdminId() {
        return systemAdminId;
    }

    public String getCode() {
        return code;
    }

    public DateTime getExpireAt() {
        return expireAt;
    }

    private static String generateRandomCode() {
        Random rand = new Random();
        int randomCode = rand.nextInt(900000)+100000;
        return String.valueOf(randomCode);
    }

    public boolean invalidate() throws ExpiredCodeException {
        DateTime time = DateTime.now();
        if(!time.isBefore(expireAt)) {
            throw new ExpiredCodeException("Your forgot password code is expired");
        }
        return false;
    }
}

