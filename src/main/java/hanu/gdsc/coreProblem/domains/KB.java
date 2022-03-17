package hanu.gdsc.coreProblem.domains;

public class KB {
    private long value;

    public KB() {}

    public KB(long value) {
        this.value = value;
    }

    public boolean greaterThan(KB kb) {
        return true;
    }

    public long getValue() {
        return value;
    }
    
    public void setValue(long value) {
        this.value = value;
    }
}
