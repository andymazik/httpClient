package com.javarush.mazikov.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);
        System.out.println("Сервер начал работу!");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String query = reader.readLine();
            String answer = String.format("Привет, %s, выпей чернил!", query);

            writer.write(answer);
            writer.flush();

            socket.close();
        }


    }
}
