package hanu.gdsc.coderAuth.services.signUp;

import hanu.gdsc.share.error.BusinessLogicError;

public class Username {
    private String value;

    public Username(String value) {
        if(value.length() < 8) {
            throw new BusinessLogicError("username cần tối thiểu 8 kí tự");
        }
    }
}
