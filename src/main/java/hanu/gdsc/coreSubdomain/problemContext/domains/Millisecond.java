package hanu.gdsc.coreSubdomain.problemContext.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.coreSubdomain.problemContext.json.MillisecondDeserializer;
import hanu.gdsc.coreSubdomain.problemContext.json.MillisecondSerializer;

@JsonSerialize(using = MillisecondSerializer.class)
@JsonDeserialize(using = MillisecondDeserializer.class)
public class Millisecond {
    private long value;

    public Millisecond plus(Millisecond that) {
        return new Millisecond(value + that.value);
    }

    public Millisecond(Long millisecond) {
        this.value = millisecond;
    }

    public static Millisecond fromSecond(Float val) {
        long m = (long) (val * 1000);
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