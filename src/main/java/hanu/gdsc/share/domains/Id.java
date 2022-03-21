package hanu.gdsc.share.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.controller.jackson.IdDeserializer;
import hanu.gdsc.share.controller.jackson.IdSerializer;
import org.bson.types.ObjectId;

@JsonSerialize(using = IdSerializer.class)
@JsonDeserialize(using = IdDeserializer.class)
public class Id {
    private String value;

    public Id(String value) {
        if (!ObjectId.isValid(value)) {
            throw new BusinessLogicError("Id không hợp lệ: '" + value + "'.", "INVALID_ID");
        }
        this.value = value;
    }

    public static Id generateRandom() {
        return new Id(new ObjectId().toString());
    }

    public String toString() {
        return value;
    }
}
