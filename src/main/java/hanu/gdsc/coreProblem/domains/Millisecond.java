package hanu.gdsc.coreProblem.domains;

public class Millisecond {
    private long value;
    
    public Millisecond(Long millisecond) {
        this.value = millisecond;
    }

    public static Millisecond fromSecond(Float val) {
        return null;
    }

    public boolean greaterThan(Millisecond millisecond) {
        return true;
    }

    public long getValue() {
        return value;
    }
}
