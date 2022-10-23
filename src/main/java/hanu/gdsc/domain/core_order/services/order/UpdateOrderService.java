package hanu.gdsc.domain.core_order.services.order;

import hanu.gdsc.domain.core_order.exceptions.InvalidStatusException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

public interface UpdateOrderService {
    public void cancel(Id orderId, Id coderId) throws NotFoundException, InvalidInputException, InvalidStatusException;
}
