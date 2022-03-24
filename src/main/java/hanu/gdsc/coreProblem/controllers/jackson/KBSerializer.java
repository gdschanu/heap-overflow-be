package hanu.gdsc.coreProblem.controllers.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.share.domains.DateTime;

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