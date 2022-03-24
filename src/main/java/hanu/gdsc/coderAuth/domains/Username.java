package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.error.BusinessLogicError;

public class Username {
    private String value;

    public Username(String value) {
        this.value = value;
        if(value.length() < 8) {
            throw new BusinessLogicError("username cần tối thiểu 8 kí tự", "INVALID_USERNAME");
        }
        if (Email.isValidEmail(value)) {
            throw new BusinessLogicError("username không được phép có định dạng email", "INVALID_USERNAME");
        }
    }
    @Override
    public String toString() {
        return value;
    }  
}
