package hanu.gdsc.core_like.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import hanu.gdsc.core_like.domains.Action;

public class ActionDeserializer extends StdDeserializer<Action>{

    protected ActionDeserializer() {
        this(null);
    }

    protected ActionDeserializer(Class<Action> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return Action.from(jsonParser.getValueAsString());
    }


    
}
