package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.HashedPassword;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.WrongCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.WrongEmail;

@Service
public class ConfirmForgotPasswordServiceImpl implements ConfirmForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    @Override
    public void confirmForgotPassword(String email, String code, String newPassword) {
        User user = userRepository.getByEmail(new Email(email));
        if (user == null) {
            throw new WrongEmail();
        }
        String codeFromDb = forgotPasswordCodeRepository.getByCoderId(user.getId()).getCode();
        if(!code.equals(codeFromDb)) {
            throw new WrongCode();
        }
        user.setPassword(HashedPassword.fromRawPassword(newPassword));
        userRepository.save(user);
    }
}
