package hanu.gdsc.practiceProblem_problem.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.io.IOException;

public class DifficultyDeserializer extends StdDeserializer<Difficulty> {
    public DifficultyDeserializer() {
        this(null);
    }

    protected DifficultyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Difficulty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            return Difficulty.from(jsonParser.getValueAsString());
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }
}
