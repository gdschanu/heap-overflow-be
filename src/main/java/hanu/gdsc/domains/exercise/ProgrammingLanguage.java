package hanu.gdsc.domains.exercise;

import java.util.Objects;

public class ProgrammingLanguage {
    public enum Type {
    }

    private Type type;

    public static ProgrammingLanguage valueOf(String value) {
        // TODO: implement
        return null;
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
}
