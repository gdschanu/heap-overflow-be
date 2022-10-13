package hanu.gdsc.core_order.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.exceptions.InvalidInputException;

public class Transaction extends IdentitifedVersioningDomainObject {
    private Id coderId;
    private Type type;
    private int balanceBefore;
    private int balanceAfter;
    private String description;
    private DateTime createdAt;

    private Transaction(Id id,
                        long version,
                        Id coderId,
                        Type type,
                        int balanceBefore,
                        int balanceAfter,
                        String description,
                        DateTime createdAt) {
        super(id, version);
        this.coderId = coderId;
        this.type = type;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Transaction create(Id coderId,
                                     Type type,
                                     int balanceBefore,
                                     int balanceAfter,
                                     String description) throws InvalidInputException {
        if (coderId == null) {
            throw new InvalidInputException("coderId must not be null.");
        }
        if (type == null) {
            throw new InvalidInputException("type must not be null.");
        }
        if (balanceBefore < 0 || balanceAfter < 0) {
            throw new InvalidInputException("balance must not be smaller than 0.");
        }
        if (type == Type.INCREASE && balanceBefore >= balanceAfter) {
            throw new InvalidInputException("balanceBefore must be smaller than balanceAfter for type INCREASE.");
        }
        if (type == Type.DECREASE && balanceBefore <= balanceAfter) {
            throw new InvalidInputException("balanceBefore must be greater than balanceAfter for type DECREASE.");
        }
        if (description == null) {
            throw new InvalidInputException("description must not be null.");
        }
        return new Transaction(
                Id.generateRandom(),
                0,
                coderId,
                type,
                balanceBefore,
                balanceAfter,
                description,
                DateTime.now()
        );
    }
}
