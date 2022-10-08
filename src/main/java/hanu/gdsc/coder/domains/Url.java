package hanu.gdsc.coder.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coder.json.UrlDeserializer;
import hanu.gdsc.coder.json.UrlSerializer;

@JsonSerialize(using = UrlSerializer.class)
@JsonDeserialize(using = UrlDeserializer.class)
public class Url {
    private String value;

    public Url(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
