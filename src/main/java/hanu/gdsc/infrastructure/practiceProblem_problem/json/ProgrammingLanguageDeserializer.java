package hanu.gdsc.infrastructure.practiceProblem_problem.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.RuntimeInvalidInputException;

import java.io.IOException;

public class ProgrammingLanguageDeserializer extends StdDeserializer<ProgrammingLanguage> {
    public ProgrammingLanguageDeserializer() {
        this(null);
    }

    protected ProgrammingLanguageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProgrammingLanguage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            return ProgrammingLanguage.from(jsonParser.getValueAsString());
        } catch (InvalidInputException e) {
            throw new RuntimeInvalidInputException(e.getMessage());
        }
    }
}
