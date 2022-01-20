package hanu.gdsc.domains.exercise;

import java.util.Objects;

public class ProgrammingLanguage {
    private Type type;

    public enum Type {
        JAVA, CPLUSPLUS, PYTHON
    }

    private ProgrammingLanguage(Type type) {
        this.type = type;
    }

    public static ProgrammingLanguage valueOf(String value) {
        try {
            return new ProgrammingLanguage(Type.valueOf(value));
        } catch (Exception e) {
            throw new Error("Invalid programming language: " + value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammingLanguage that = (ProgrammingLanguage) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return type == Type.CPLUSPLUS ? "C++" : type.toString();
    }
}
