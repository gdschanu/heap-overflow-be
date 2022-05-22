package hanu.gdsc.share.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.controller.jackson.IdDeserializer;
import hanu.gdsc.share.controller.jackson.IdSerializer;
import hanu.gdsc.share.error.InvalidInputError;
import org.bson.types.ObjectId;

import java.util.Objects;

@JsonSerialize(using = IdSerializer.class)
@JsonDeserialize(using = IdDeserializer.class)
public class Id {
    private String value;

    public Id(String value) {
        if (!ObjectId.isValid(value)) {
            throw new InvalidInputError("Invalid Id: '" + value + "'.");
        }
        this.value = value;
    }

    public static Id generateRandom() {
        return new Id(new ObjectId().toString());
    }

    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
