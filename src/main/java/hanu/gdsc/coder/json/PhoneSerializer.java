package hanu.gdsc.coder.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hanu.gdsc.coder.domains.Phone;

import java.io.IOException;

public class PhoneSerializer extends StdSerializer<Phone> {
    protected PhoneSerializer() {
        this(null);
    }


    public PhoneSerializer(Class<Phone> t) {
        super(t);
    }

    @Override
    public void serialize(Phone d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(d.toString());
    }
}