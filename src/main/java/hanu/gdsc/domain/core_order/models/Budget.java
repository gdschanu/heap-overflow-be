package hanu.gdsc.domain.core_order.models;

import hanu.gdsc.domain.core_order.exceptions.InsufficientBudgetException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

public class Budget extends VersioningDomainObject {
    private Id coderId;
    private int balance;
    private DateTime createdAt;
    private DateTime updatedAt;

    private Budget(long version,
                   Id coderId,
                   int balance,
                   DateTime createdAt,
                   DateTime updatedAt) {
        super(version);
        this.coderId = coderId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Budget create(Id coderId) throws InvalidInputException {
        if (coderId == null)
            throw new InvalidInputException("coderId must not be null.");
        return new Budget(
                0,
                coderId,
                0,
                DateTime.now(),
                DateTime.now()
        );
    }

    public Transaction increase(int value, String reason) throws InvalidInputException {
        // validate input
        if (value <= 0) {
            throw new InvalidInputException("value must be greater than 0.");
        }
        if (reason == null) {
            throw new InvalidInputException("reason must not be null.");
        }
        // logic
        balance += value;
        refreshUpdatedAt();
        return Transaction.create(
                coderId,
                Type.INCREASE,
                balance - value,
                balance,
                reason
        );
    }

    public Transaction decreaseBy(Order order) throws InsufficientBudgetException, InvalidInputException {
        return decrease(order.totalPrice(), "Decrease budget for order " + order.getId() + ".");
    }

    public Transaction decrease(int value, String reason) throws InsufficientBudgetException, InvalidInputException {
        // validate input
        if (value <= 0) {
            throw new InvalidInputException("value must be greater than 0.");
        }
        if (reason == null) {
            throw new InvalidInputException("reason must not be null.");
        }
        // logic
        if (value > balance) {
            throw new InsufficientBudgetException("Budget has only " + balance
                    + " balance, but you are trying to subtract " + value + ".");
        }
        balance -= value;
        refreshUpdatedAt();
        return Transaction.create(
                coderId,
                Type.DECREASE,
                balance + value,
                balance,
                reason
        );
    }

    private void refreshUpdatedAt() {
        updatedAt = DateTime.now();
    }

    public Id getCoderId() {
        return coderId;
    }

    public int getBalance() {
        return balance;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }
}
