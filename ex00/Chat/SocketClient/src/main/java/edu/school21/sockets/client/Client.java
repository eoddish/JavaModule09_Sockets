package edu.school21.sockets.client;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Scanner scanner;

    public void registration(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        scanner = new Scanner(System.in);
        String line;
        while (!(line = reader.readLine()).equals("Successful!")) {
            System.out.println(line);
            writer.println(scanner.nextLine());
        }
        System.out.println(line);
        writer.close();
        reader.close();
        scanner.close();
        clientSocket.close();
    }
}
