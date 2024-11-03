package org.topicsswe.userservice.application.configuration;

import com.password4j.Hash;
import com.password4j.Password;
import org.springframework.context.annotation.Bean;

//TODO finish
public class PasswordHasher {

    private String salt; //TODO add salt in config file
    private String pepper; // TODO add pepper in config file

    public Hash passwordHashing(String pwd) {
        return Password.hash(pwd)
                .addSalt(salt)
                .addPepper(pepper)
                .withArgon2();
    }

    public boolean passwordCheck(String pwd, String hash) {
        return Password.check(pwd, hash)
                .addSalt(salt)
                .addPepper(pepper)
                .withArgon2();
    }
}
