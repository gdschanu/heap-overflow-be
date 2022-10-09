package hanu.gdsc.share.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.io.IOException;

public class DateTimeDeserializer extends StdDeserializer<DateTime> {

    protected DateTimeDeserializer() {
        this(null);
    }


    protected DateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            return new DateTime(jsonParser.getValueAsString());
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }
}
