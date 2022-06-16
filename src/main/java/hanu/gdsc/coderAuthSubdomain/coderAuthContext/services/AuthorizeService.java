package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.UnauthorizedError;


public interface AuthorizeService {
    public Id authorize(String token) throws UnauthorizedError;
}
