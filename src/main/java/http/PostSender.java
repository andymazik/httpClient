package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.entity.Post;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        Post post = createPost("Manager", "bodybody", 34);
        String s = postToJson(post);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .POST(HttpRequest.BodyPublishers.ofString(s))
                .build();

        //простейший вариант
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(s);
        System.out.println("\n****************\n");
        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.body());
    }

    private static boolean isGoodResponse(HttpResponse<String> response) {
        return response.statusCode() == 201;
    }

    private static Post createPost(String title, String body, int userId) {

        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    private static String postToJson(Post post) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(post);

    }
}
