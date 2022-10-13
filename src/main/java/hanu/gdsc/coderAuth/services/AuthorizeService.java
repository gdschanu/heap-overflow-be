package hanu.gdsc.coderAuth.services;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.UnauthorizedException;


public interface AuthorizeService {
    public Id authorize(String token) throws UnauthorizedException, InvalidInputException;
}
