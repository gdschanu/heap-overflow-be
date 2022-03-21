package hanu.gdsc.share.controller.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.share.domains.DateTime;

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
        return new DateTime(jsonParser.getValueAsString());
    }
}
