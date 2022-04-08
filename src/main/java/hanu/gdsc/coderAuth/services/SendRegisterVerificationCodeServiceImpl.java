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

@Service
public class SendRegisterVerificationCodeServiceImpl implements SendRegisterVerificationCodeService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendRegisterVerificationCodeService(String id) {
        Id newId = new Id(id);
        User user = userRepository.getById(newId);
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        Email fromAddress = new Email("gdschanu.linhdt0102@gmail.com");
        String subject = "Verify your email to register";
        Id codeId = Id.generateRandom();
        String code = RegisterVerificationCode.generateRandom();
        DateTime expireAt = DateTime.now().plusMinutes(100);
        RegisterVerificationCode registerVerificationCode = new RegisterVerificationCode(codeId, code, expireAt);
        registerVerificationCodeRepository.save(registerVerificationCode);

        String content = "Dear " + name + ",\n"
        +"Use this code below to verify your account \n"
        + code +"\n"
        + "Thank you,"
        +"Hanu gcsd";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        try {
            helper.setFrom(new InternetAddress(fromAddress.toString()));
            helper.setTo(new InternetAddress(toAddress.toString()));
            helper.setSubject(subject);
            helper.setText(content);

        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
