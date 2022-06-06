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

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    public void registration(int port) throws IOException {
         serverSocket = new ServerSocket(port);
         clientSocket = serverSocket.accept();
         writer = new PrintWriter(clientSocket.getOutputStream(), true);
         reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         writer.println("Hello from Server!");
         String line = reader.readLine();
         if (line.equals("signUp")) {
             writer.println("Enter username:");
             String username = reader.readLine();
             writer.println("Enter password:");
             String password = reader.readLine();
             writer.println("Successful!");
             ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
             UsersService usersService = context.getBean("usersService", UsersService.class);
             UsersRepository usersRepository = context.getBean("usersRepository", UsersRepository.class);
             User user = new User(1L, username, usersService.encodePassword(password));
             usersRepository.save(user);
             List<User> list = usersRepository.findAll();
             for (int i = 0; i < list.size(); i++) {
                 user = list.get(i);
                 System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword());
             }
         }
        reader.close();
        writer.close();
        clientSocket.close();
        serverSocket.close();
    }
}
