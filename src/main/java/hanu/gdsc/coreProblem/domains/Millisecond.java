package hanu.gdsc.coreProblem.domains;

public class Millisecond {
    private Long value;

    public Millisecond(Long millisecond) {
        this.value = millisecond;
    }
    
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public boolean greaterThan(Millisecond millisecond) {
        return true;
    }
}
