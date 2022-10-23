package hanu.gdsc.infrastructure.share.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.RuntimeInvalidInputException;

import java.io.IOException;

public class IdDeserializer extends StdDeserializer<Id> {
    protected IdDeserializer() {
        this(null);
    }


    protected IdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Id deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            return new Id(jp.getValueAsString());
        } catch (InvalidInputException e) {
            throw new RuntimeInvalidInputException(e.getMessage());
        }
    }
}
