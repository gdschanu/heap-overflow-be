package hanu.gdsc.infrastructure.practiceProblem_problem.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.RuntimeInvalidInputException;

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
            throw new RuntimeInvalidInputException(e.getMessage());
        }
    }
}
