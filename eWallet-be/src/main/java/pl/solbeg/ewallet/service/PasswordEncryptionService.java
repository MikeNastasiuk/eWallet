package pl.solbeg.ewallet.service;

import org.springframework.stereotype.Service;
import pl.solbeg.ewallet.dto.TokenData;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.exeption.ErrorCodeEnum;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Service
public class PasswordEncryptionService {

    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final String ENCRYPT_ALGORITHM = "PBKDF2WithHmacSHA1";

    public boolean authenticate (String checkedPassword, byte[] customerPassword, byte[] salt) {
        try {
            byte[] encryptedCheckedPassword = getEncryptedPassword(checkedPassword, salt);
            return Arrays.equals(customerPassword, encryptedCheckedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new DecodedException(ErrorCodeEnum.CUSTOMER_LOGIN_FAIL);
        }

    }

    public byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ENCRYPT_ALGORITHM);

        byte[] pas = factory.generateSecret(spec).getEncoded();
        return pas;
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance(SALT_ALGORITHM);
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public TokenData getTokenData(String token) {
        String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        return TokenData.builder()
                .login(decoded.split(":")[0])
                .password(decoded.split(":")[1])
                .build();
    }
}
