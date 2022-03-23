package hanu.gdsc.practiceProblem.controllers.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.share.domains.DateTime;

import java.io.IOException;

public class DifficultySerializer extends StdSerializer<Difficulty> {
    protected DifficultySerializer() {
        this(null);
    }


    public DifficultySerializer(Class<Difficulty> t) {
        super(t);
    }

    @Override
    public void serialize(Difficulty d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(d.toString());
    }
}