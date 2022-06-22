package hanu.gdsc.coderAuth_coderAuth.domains;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import hanu.gdsc.coderAuth_coderAuth.errors.InvalidPassword;
import hanu.gdsc.share.error.BusinessLogicError;

public class HashedPassword {
    private String hashedPassword;

    private HashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static HashedPassword fromRawPassword(String rawPassword) {
        if (rawPassword.length() < 3 || rawPassword.length() > 15) {
            throw new InvalidPassword("Password length should be between 3 and 15 characters");
        }
        if (!containsLetter(rawPassword) || !containsNumber(rawPassword)) {
            throw new InvalidPassword("Password need both number and letter");
        }
        String hashedPassword = encode(rawPassword);
        return new HashedPassword(hashedPassword);
    }

    public static HashedPassword fromHashedPassword(String hashedPassword) {
        return new HashedPassword(hashedPassword);
    }

    public String toHashedPasswordString() {
        return hashedPassword;
    }

    private static boolean containsNumber(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= '9' && c >= '0') {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean containsLetter(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A') {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }  

    private static String encode(String rawPassword) {
        MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(rawPassword.getBytes());
                byte[] digest = md.digest();
                String myHash = DatatypeConverter
                        .printHexBinary(digest).toUpperCase();
                return myHash;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new BusinessLogicError("No such algorithm exception", "NO_SUCH_ALGORITHM");
            }
    }
}
