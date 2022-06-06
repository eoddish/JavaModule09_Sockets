package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;

import java.util.Optional;

public interface MessagesRepository<T> extends CrudRepository<Message> {
    public Optional<T> findByText(String text);
}
