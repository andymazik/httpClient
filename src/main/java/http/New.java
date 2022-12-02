package http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

public class New {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javarush.com"))
                .version(HttpClient.Version.HTTP_1_1)
                .timeout(Duration.ofMillis(5000))
                .header("Content-Type", "text/plain;charset=UTF-8")
                .GET()
                //.POST(HttpRequest.BodyPublishers.ofString("HEllo"))
                //  .POST(HttpRequest.BodyPublishers.fromFile(Path.of()))
                .build();

        //простейший вариант
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //посложнее
//        HttpResponse<String> response = HttpClient.newBuilder()
//                .followRedirects(HttpClient.Redirect.ALWAYS)
//                .build()
//                .send(request, HttpResponse.BodyHandlers.ofString());



        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.body());
    }
}
