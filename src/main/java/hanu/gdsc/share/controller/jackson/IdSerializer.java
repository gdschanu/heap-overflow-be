package hanu.gdsc.share.controller.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.share.domains.Id;

import java.io.IOException;

public class IdSerializer extends StdSerializer<Id> {
    protected IdSerializer() {
        this(null);
    }


    public IdSerializer(Class<Id> t) {
        super(t);
    }

    @Override
    public void serialize(Id id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(id.toString());
    }
}
