package hanu.gdsc.infrastructure.core_like.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import hanu.gdsc.domain.core_like.models.Action;

public class ActionSerializer extends StdSerializer<Action>{

    protected ActionSerializer() {
        this(null);
    }

    protected ActionSerializer(Class<Action> t) {
        super(t);
    }

    @Override
    public void serialize(Action action, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(action.toString());
    }
    
}
