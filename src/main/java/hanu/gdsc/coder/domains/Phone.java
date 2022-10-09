package hanu.gdsc.coder.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coder.json.PhoneDeserializer;
import hanu.gdsc.coder.json.PhoneSerializer;
import hanu.gdsc.share.exceptions.BusinessLogicException;
import hanu.gdsc.share.exceptions.InvalidInputException;

@JsonSerialize(using = PhoneSerializer.class)
@JsonDeserialize(using = PhoneDeserializer.class)
public class Phone {
    private String value;

    public Phone(String value) throws InvalidInputException {
        if(value.length() == 10) {
            this.value = value;
        } else {
            throw new InvalidInputException("Invalid phone number: " + value + ".");
        }
    }
}
