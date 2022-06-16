package hanu.gdsc.coreSubdomain.problemContext.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coreSubdomain.problemContext.json.KBDeserializer;
import hanu.gdsc.coreSubdomain.problemContext.json.KBSerializer;

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

    public KB plus(KB that) {
        return new KB(value + that.value);
    }

    public KB divide(double val) {
        return new KB(value / val);
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
