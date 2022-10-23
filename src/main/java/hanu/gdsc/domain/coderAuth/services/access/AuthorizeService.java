package hanu.gdsc.domain.coderAuth.services.access;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;


public interface AuthorizeService {
    public Id authorize(String token) throws UnauthorizedException, InvalidInputException;
}
