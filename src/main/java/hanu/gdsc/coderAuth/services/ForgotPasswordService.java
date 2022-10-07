package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.ForgotPasswordCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.errors.WrongEmail;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;

@Service
public class ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    @Autowired
    private SendMailService sendMailService;

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
