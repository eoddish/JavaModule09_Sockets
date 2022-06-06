package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
    public boolean matchPassword(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

}
