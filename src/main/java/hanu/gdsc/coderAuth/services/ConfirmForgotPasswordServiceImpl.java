package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.ForgotPasswordCodeRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.error.BusinessLogicError;

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
            throw new BusinessLogicError("Your email is wrong", "WRONG_EMAIL");
        }
        String codeFromDb = forgotPasswordCodeRepository.getByCoderId(user.getId()).getCode();
        if(!code.equals(codeFromDb)) {
            throw new BusinessLogicError("your code is wrong", "WRONG_CODE");
        }
        user.setPassword(HashedPassword.fromRawPassword(newPassword));
        userRepository.save(user);
    }
}
