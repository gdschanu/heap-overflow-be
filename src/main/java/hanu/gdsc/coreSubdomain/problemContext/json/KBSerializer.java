package hanu.gdsc.coreSubdomain.problemContext.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.coreSubdomain.problemContext.domains.KB;

import java.io.IOException;

public class KBSerializer extends StdSerializer<KB> {
    protected KBSerializer() {
        this(null);
    }


    public KBSerializer(Class<KB> t) {
        super(t);
    }

    @Override
    public void serialize(KB d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(d.getValue());
    }
}