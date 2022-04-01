package hanu.gdsc.coreProblem.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coreProblem.controllers.jackson.KBDeserializer;
import hanu.gdsc.coreProblem.controllers.jackson.KBSerializer;

@JsonSerialize(using = KBSerializer.class)
@JsonDeserialize(using = KBDeserializer.class)
public class KB {
    private double value;

    public KB(long value) {
        this.value = value;
    }

    public KB(double value) {
        this.value = value;
    }

    public boolean greaterThan(KB that) {
        return this.value > that.value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "KB{" +
                "value=" + value +
                '}';
    }
}
