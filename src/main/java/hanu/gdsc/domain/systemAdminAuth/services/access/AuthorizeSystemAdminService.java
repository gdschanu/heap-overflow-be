package hanu.gdsc.domain.systemAdminAuth.services.access;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import hanu.gdsc.domain.share.models.Id;


public interface AuthorizeSystemAdminService {
    public Id authorize(String token) throws UnauthorizedException, InvalidInputException;
}
