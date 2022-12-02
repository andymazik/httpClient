package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("javarush.com",80);

//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("ya.ru",80), 2000);
//
//        Scanner scanner = new Scanner(socket.getInputStream());
//        while (scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
//        }

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        String query = "GET / HTTP/1.1\r\nHost:javarush.com/\r\n";
        outputStream.write(query.getBytes());
        outputStream.write('\n');
        outputStream.flush();

        int value;
        while ((value = inputStream.read()) > 0) {
            System.out.print((char)value);
        }

        socket.close();

    }
}
