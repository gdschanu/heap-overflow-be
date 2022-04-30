package hanu.gdsc.coderAuth.services;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SendRegisterVerificationCodeServiceImpl implements SendRegisterVerificationCodeService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendRegisterVerificationCodeService(Id coderId) {
        User user = userRepository.getByCoderId(coderId);
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        Email fromAddress = new Email("dtlinh010202@gmail.com");
        String subject = "Verify your email to register";
        String code = RegisterVerificationCode.generateRandom();
        DateTime expireAt = DateTime.now().plusMinutes(100);
        RegisterVerificationCode registerVerificationCode = new RegisterVerificationCode(coderId, code, expireAt);
        registerVerificationCodeRepository.save(registerVerificationCode);

        String content = "Dear " + name + ",\n"
        +"Use this code below to verify your account \n"
        + code +"\n"
        + "Thanks,\n"
        +"Hanu gcsd";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        try {
            helper.setFrom(new InternetAddress(fromAddress.toString()));
            helper.setTo(new InternetAddress(toAddress.toString()));
            helper.setSubject(subject);
            helper.setText(content);

        } catch (MessagingException exception) {
            throw new BusinessLogicError("Error in sending email", "EMAIL_SENDING_ERROR");
        }
        javaMailSender.send(message);
    }

}
