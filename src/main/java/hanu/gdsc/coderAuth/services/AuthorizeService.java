package hanu.gdsc.coderAuth.services;

import hanu.gdsc.share.domains.Id;


public interface AuthorizeService {
    public Id authorize(String token);
    public Id authorizeUnconfirmedRegistration(String token);
}
