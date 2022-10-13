package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.ForgotPasswordCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.exceptions.EmailSendingException;
import hanu.gdsc.coderAuth.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    @Autowired
    private SendMailService sendMailService;

    public void forgotPassword(String email) throws InvalidInputException, NotFoundException, EmailSendingException {
        User user = userRepository.getByEmail(new Email(email));
        if (user == null) {
            throw new NotFoundException("Unknown email");
        }
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        ForgotPasswordCode forgotPasswordCode = ForgotPasswordCode.createForgotPasswordCode(user.getId());
        forgotPasswordCodeRepository.save(forgotPasswordCode);

        sendMailService.sendMail(toAddress, name, forgotPasswordCode.getCode());
    }
}
