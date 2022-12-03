package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Old {
    public static void main(String[] args) throws IOException {
        //HttpURLConnection connection = (HttpURLConnection) new URL("https://javarush.ru").openConnection();
        HttpURLConnection connection = (HttpURLConnection) new URL("https://jsonplaceholder.typicode.com/posts").openConnection();

        StringBuilder content = new StringBuilder();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader;

        if (responseCode <= 299) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (bufferedReader.ready()) {
                content.append(bufferedReader.readLine());
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            content.append(bufferedReader.readLine());
        }


//        bufferedReader.close();
//        JsonParser.parseJson(content.toString());
//        connection.disconnect();
        System.out.println(content);
    }
}
