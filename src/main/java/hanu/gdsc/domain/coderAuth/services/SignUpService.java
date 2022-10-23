package hanu.gdsc.domain.coderAuth.services;

import hanu.gdsc.domain.coder.models.Gender;
import hanu.gdsc.domain.coder.models.Url;
import hanu.gdsc.domain.coder.services.CreateCoderService;
import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.HashedPassword;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.models.Username;
import hanu.gdsc.domain.coderAuth.exceptions.ExistedUsernameOrEmailException;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
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
