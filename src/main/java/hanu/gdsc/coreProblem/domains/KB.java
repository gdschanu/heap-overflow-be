package hanu.gdsc.coreProblem.domains;

public class KB {
    private float value;

    public KB(long value) {
        this.value = value;
    }

    public KB(float value) {
        this.value = value;
    }

    public boolean greaterThan(KB kb) {
        throw new Error("Unimplemented");
    }

    public float getValue() {
        return value;
    }
}
