package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.context.annotation.*;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class Server {
    private ServerSocket serverSocket;


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            new PrivateServer(serverSocket.accept()).runServer();
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
    }

    private static class PrivateServer extends Thread {
        private Socket clientSocket;
        private PrintWriter writer;
        private BufferedReader reader;

        public PrivateServer(Socket socket) {
            this.clientSocket = socket;
        }

        public void runServer() throws IOException {

            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer.println("Hello from Server!");
            String line = reader.readLine();
            if (line.equals("signUp")) {
                writer.println("Enter username:");
                String username = reader.readLine();
                writer.println("Enter password:");
                String password = reader.readLine();
                ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
                UsersService usersService = context.getBean("usersService", UsersService.class);
                UsersRepository usersRepository = context.getBean("usersRepository", UsersRepository.class);
                User user = new User(1L, username, usersService.encodePassword(password));
                usersRepository.save(user);
                while(!(line = reader.readLine()).equals("Exit")) {
                    System.out.println(user.getUsername() + ": " + line);
                }
                writer.println("Exit");

            }
            reader.close();
            writer.close();
            clientSocket.close();
        }

    }
}
