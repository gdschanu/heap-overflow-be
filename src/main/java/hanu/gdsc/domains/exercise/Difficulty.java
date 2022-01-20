package hanu.gdsc.domains.exercise;

import java.util.Objects;

public class Difficulty {
    private Type type;

    public enum Type {
        HARD, MEDIUM, EASY
    }

    public Difficulty(Type type) {
        this.type = type;
    }

    public static Difficulty valueOf(String value) {
        try {
            return new Difficulty(Type.valueOf(value));
        } catch (Exception e) {
            throw new Error("Invalid difficulty: " + value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Difficulty that = (Difficulty) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
