package edu.school21.sockets.app;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            throw new Exception("Error: Wrong argument!");
        String argument[] = args[0].split("=");
        if (!argument[0].equals("--port"))
            throw new Exception("Error: Wrong argument!");
        int port = Integer.parseInt(argument[1]);
        Server server = new Server();
        server.start(port);
    }
}
