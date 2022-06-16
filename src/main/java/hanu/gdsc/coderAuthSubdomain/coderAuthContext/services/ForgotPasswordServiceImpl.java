package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.ForgotPasswordCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.WrongEmail;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user.UserRepository;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.getByEmail(new Email(email));
        if(user == null) {
            throw new WrongEmail();
        }
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        ForgotPasswordCode forgotPasswordCode = ForgotPasswordCode.createForgotPasswordCode(user.getId());
        forgotPasswordCodeRepository.save(forgotPasswordCode);

        sendMailService.sendMail(toAddress, name, forgotPasswordCode.getCode());
    }
}
