package hanu.gdsc.core_order.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.util.List;

public class Order extends IdentitifedVersioningDomainObject {
    private Id coderId;
    private List<OrderLineItem> lineItems;
    private Status status;
    private DateTime createdAt;

    private Order(Id id,
                  long version,
                  Id coderId,
                  List<OrderLineItem> lineItems,
                  Status status,
                  DateTime createdAt) {
        super(id, version);
        this.coderId = coderId;
        this.lineItems = lineItems;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order create(Id coderId,
                               List<OrderLineItem> lineItems) throws InvalidInputException {
        if (coderId == null) {
            throw new InvalidInputException("coderId must not be null.");
        }
        if (lineItems == null || lineItems.size() == 0) {
            throw new InvalidInputException("must have more than 0 line items.");
        }
        for (OrderLineItem lineItem : lineItems) {
            if (lineItem == null) {
                throw new InvalidInputException("lineItems must not contains null element.");
            }
        }
        return new Order(
                Id.generateRandom(),
                0,
                coderId,
                lineItems,
                Status.PENDING,
                DateTime.now()
        );
    }
}
