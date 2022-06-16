package hanu.gdsc.practiceProblemSubdomain.problemContext.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hanu.gdsc.coreSubdomain.problemContext.domains.ProgrammingLanguage;

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
        return ProgrammingLanguage.from(jsonParser.getValueAsString());
    }
}
