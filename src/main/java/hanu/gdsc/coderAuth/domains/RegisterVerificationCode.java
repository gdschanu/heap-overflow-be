package hanu.gdsc.coderAuth.domains;

import java.util.Random;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;

public class RegisterVerificationCode {
    private Id id;
    private String code;
    private DateTime expiredAt;
    
    public RegisterVerificationCode(Id id, String code, DateTime expiredAt) {
        this.id = id;
        this.code = code;
        this.expiredAt = expiredAt;
    }
    
    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(DateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public static String generateRandom() {
        Random rand = new Random();
        int randomCode = rand.nextInt(900000)+100000;
        return String.valueOf(randomCode);
    }
}
