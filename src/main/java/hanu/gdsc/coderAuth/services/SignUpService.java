package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coder.domains.Gender;
import hanu.gdsc.coder.domains.Url;
import hanu.gdsc.coder.services.CreateCoderService;
import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.exceptions.ExistedUsernameOrEmailException;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateCoderService createCoderService;

    private static final String defaultAvatar = "https://media.istockphoto.com/vectors/male-face-silhouette-or-icon-man-avatar-profile-unknown-or-anonymous-vector-id1087531642?k=20&m=1087531642&s=170667a&w=0&h=ge3fq1Dw0-J2FoW96c8klSiHyOnitVhReUUuIIYqtvw=";

    public void signUpService(String email, String username, String password) throws InvalidInputException, ExistedUsernameOrEmailException {
        Email newEmail = new Email(email);
        Username newUsername = new Username(username);
        HashedPassword newPassword = HashedPassword.fromRawPassword(password);
        if (userRepository.getByUsername(new Username(username)) == null &&
                userRepository.getByEmail(new Email(email)) == null) {
            User user = User.createUser(
                    newEmail,
                    newUsername,
                    newPassword,
                    createCoderService.create(
                            new CreateCoderService.Input(
                                    newUsername.toString(),
                                    0,
                                    new Url(defaultAvatar),
                                    null,
                                    null,
                                    null,
                                    Gender.UNKNOWN,
                                    null
                            )
                    )
            );
            user.setPassword(newPassword);
            userRepository.save(user);
        } else {
            throw new ExistedUsernameOrEmailException();
        }
    }
}
