package hanu.gdsc.domain.coderAuth.services.password;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.ForgotPasswordCode;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.exceptions.EmailSendingException;
import hanu.gdsc.domain.coderAuth.mail.SendMail;
import hanu.gdsc.domain.coderAuth.repositories.ForgotPasswordCodeRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    @Autowired
    private SendMail sendMail;

    public void forgotPassword(String email) throws InvalidInputException, NotFoundException, EmailSendingException {
        User user = userRepository.getByEmail(new Email(email));
        if (user == null) {
            throw new NotFoundException("Unknown email");
        }
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        ForgotPasswordCode forgotPasswordCode = ForgotPasswordCode.createForgotPasswordCode(user.getId());
        forgotPasswordCodeRepository.save(forgotPasswordCode);

        sendMail.sendMail(toAddress, name, forgotPasswordCode.getCode());
    }
}
