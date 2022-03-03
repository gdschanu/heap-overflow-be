package hanu.gdsc.share.domains;

import hanu.gdsc.share.error.BusinessLogicError;

import java.util.UUID;

public class Id {
    private UUID value;

    public Id(UUID value) {
        this.value = value;
    }

    public Id(String value) {
        try {
            this.value = UUID.fromString(value);
        } catch (Exception e) {
            throw new BusinessLogicError("Id không hợp lệ: '" + value + "'.");
        }
    }

    public static Id generateRandom() {
        return new Id(UUID.randomUUID());
    }
}
