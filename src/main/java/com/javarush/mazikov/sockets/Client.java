package com.javarush.mazikov.sockets;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4444);

        System.out.println("Клиент начал работу");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String name = "Жохан\n";
        bufferedWriter.write(name);
        //bufferedWriter.newLine();
        bufferedWriter.flush();

        String answer = bufferedReader.readLine();
        System.out.println("Ответ от сервера: " + answer);

        socket.close();
    }
}
