package hanu.gdsc.infrastructure.coder.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.domain.coder.models.Phone;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.RuntimeInvalidInputException;

import java.io.IOException;

public class PhoneDeserializer extends StdDeserializer<Phone> {
    public PhoneDeserializer() {
        this(null);
    }

    protected PhoneDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Phone deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            return new Phone(jsonParser.getValueAsString());
        } catch (InvalidInputException e) {
            throw new RuntimeInvalidInputException(e.getMessage());
        }
    }
}