package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.errors.WrongCode;
import hanu.gdsc.coderAuth.errors.WrongEmail;
import hanu.gdsc.coderAuth.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;

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
