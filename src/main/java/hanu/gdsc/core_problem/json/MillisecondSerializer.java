package hanu.gdsc.core_problem.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.core_problem.domains.Millisecond;

import java.io.IOException;

public class MillisecondSerializer extends StdSerializer<Millisecond> {
    protected MillisecondSerializer() {
        this(null);
    }


    public MillisecondSerializer(Class<Millisecond> t) {
        super(t);
    }

    @Override
    public void serialize(Millisecond d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(d.getValue());
    }
}