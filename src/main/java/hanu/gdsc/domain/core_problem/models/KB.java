package hanu.gdsc.domain.core_problem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.infrastructure.core_problem.json.KBDeserializer;
import hanu.gdsc.infrastructure.core_problem.json.KBSerializer;

@JsonSerialize(using = KBSerializer.class)
@JsonDeserialize(using = KBDeserializer.class)
public class KB {
    private double value;

    public static KB max(KB a, KB b) {
        if (a.greaterThan(b))
            return a;
        return b;
    }

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
