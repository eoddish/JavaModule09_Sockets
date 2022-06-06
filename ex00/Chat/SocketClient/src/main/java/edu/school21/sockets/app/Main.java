package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        String argument[] = args[0].split("=");
        if (!argument[0].equals("--server-port"))
            throw new Exception("Error: Wrong argument!");
        int port = Integer.parseInt(argument[1]);
        Client client = new Client();
        client.registration("127.0.0.1", port);
    }
}
