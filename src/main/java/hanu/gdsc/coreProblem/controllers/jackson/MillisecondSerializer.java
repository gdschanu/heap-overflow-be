package hanu.gdsc.coreProblem.controllers.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.share.domains.DateTime;

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