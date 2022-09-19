package hanu.gdsc.core_problem.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.core_problem.json.MillisecondDeserializer;
import hanu.gdsc.core_problem.json.MillisecondSerializer;

@JsonSerialize(using = MillisecondSerializer.class)
@JsonDeserialize(using = MillisecondDeserializer.class)
public class Millisecond {
    private long value;

    public static Millisecond max(Millisecond a, Millisecond b) {
        if (a.greaterThan(b))
            return a;
        return b;
    }

    public Millisecond plus(Millisecond that) {
        return new Millisecond(value + that.value);
    }

    public Millisecond(long millisecond) {
        this.value = millisecond;
    }

    public long toSecond() {
        return value / 1000;
    }

    public static Millisecond fromSecond(Float val) {
        long m = Math.round(val * 1000);
        return new Millisecond(m);
    }

    public boolean greaterThan(Millisecond that) {
        return this.value > that.value;
    }

    public boolean greaterThan(long that) {
        return this.value > that;
    }

    public long getValue() {
        return value;
    }

    public Millisecond divide(int val) {
        return new Millisecond(value / val);
    }

    @Override
    public String toString() {
        return "Millisecond{" +
                "value=" + value +
                '}';
    }
}
