package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

public interface UsersService {
    public String encodePassword(String password);
    public boolean matchPassword(String password, User user);
}
