package hanu.gdsc.coder.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coder.json.PhoneDeserializer;
import hanu.gdsc.coder.json.PhoneSerializer;
import hanu.gdsc.share.error.BusinessLogicError;

@JsonSerialize(using = PhoneSerializer.class)
@JsonDeserialize(using = PhoneDeserializer.class)
public class Phone {
    private String value;

    public Phone(String value) {
        if(value.length() == 10) {
            this.value = value;
        } else {
            throw new BusinessLogicError("Invalid phone number", null);
        }
    }
}
