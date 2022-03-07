package hanu.gdsc.coreProblem.domains;

public class Millisecond {
    private long value;

    public Millisecond(Long millisecond) {
        this.value = millisecond;
    }
    
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public boolean greaterThan(Millisecond millisecond) {
        return true;
    }
}
