package hanu.gdsc.coder.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.coder.domains.Url;

import java.io.IOException;

public class UrlSerializer  extends StdSerializer<Url> {
    protected UrlSerializer() {
        this(null);
    }


    public UrlSerializer(Class<Url> t) {
        super(t);
    }

    @Override
    public void serialize(Url d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(d.toString());
    }
}