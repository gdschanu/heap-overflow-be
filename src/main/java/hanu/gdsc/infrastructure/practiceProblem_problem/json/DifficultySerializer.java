package hanu.gdsc.infrastructure.practiceProblem_problem.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;

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