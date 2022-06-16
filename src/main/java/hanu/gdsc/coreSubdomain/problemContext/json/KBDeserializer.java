package hanu.gdsc.coreSubdomain.problemContext.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.coreSubdomain.problemContext.domains.KB;

import java.io.IOException;


public class KBDeserializer extends StdDeserializer<KB> {
    public KBDeserializer() {
        this(null);
    }

    protected KBDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public KB deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new KB(jsonParser.getValueAsDouble());
    }
}