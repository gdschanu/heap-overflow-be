package hanu.gdsc.share.domains;

import hanu.gdsc.share.error.BusinessLogicError;
import org.bson.types.ObjectId;

public class Id {
    private String value;

    public Id() {
    }


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
