package hanu.gdsc.share.controller.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.share.domains.Id;

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
        return new Id(jp.getValueAsString());
    }
}
