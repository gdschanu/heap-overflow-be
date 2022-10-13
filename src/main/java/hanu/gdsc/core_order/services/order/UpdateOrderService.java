package hanu.gdsc.core_order.services.order;

import hanu.gdsc.core_order.exceptions.InvalidStatusException;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.InvalidStateException;
import hanu.gdsc.share.exceptions.NotFoundException;

public interface UpdateOrderService {
    public void cancel(Id orderId, Id coderId) throws NotFoundException, InvalidInputException, InvalidStatusException;
}
