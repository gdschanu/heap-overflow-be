package hanu.gdsc.coder.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.coder.domains.Url;

import java.io.IOException;

public class UrlDeserializer extends StdDeserializer<Url> {
    public UrlDeserializer() {
        this(null);
    }

    protected UrlDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Url deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new Url(jsonParser.getValueAsString());
    }
}