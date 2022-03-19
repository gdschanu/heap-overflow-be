package hanu.gdsc.coreProblem.domains;

public class Millisecond {
    private long value;
    
    public Millisecond(Long millisecond) {
        this.value = millisecond;
    }

    public static Millisecond fromSecond(Float val) {
        long m = (long) (val * 1000);
        return new Millisecond(m);
    }

    public boolean greaterThan(Millisecond millisecond) {
        return true;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Millisecond{" +
                "value=" + value +
                '}';
    }
}
