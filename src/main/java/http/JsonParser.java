package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import http.entity.Post;
import http.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class JsonParser {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .version(HttpClient.Version.HTTP_1_1)
                //.timeout(Duration.ofMillis(5000))
                //.header("Content-Type", "application/json")
                .GET()
                //.POST(HttpRequest.BodyPublishers.ofString("HEllo"))
                //  .POST(HttpRequest.BodyPublishers.fromFile(Path.of()))
                .build();

        //простейший вариант
        //HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //посложнее
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        //еще сложнее, возможно использовать только с sendAsync
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                        .thenApply(HttpResponse::body)
//                        .thenAccept(System.out::println)
//                        .join();

        //вызов метода для парсинга
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                        .thenApply(HttpResponse::body)
//                        .thenAccept(JsonParser::parseJson)
//                        .join();


//        System.out.println(response.statusCode());
//        System.out.println(response.headers());
//        System.out.println(response.body());
        List<User> users = advancedParse(response);
        for (User user : users) {
            System.out.println(user);
        }

    }

    public static void parseJson(String body){
        JSONArray data = new JSONArray(body);
        for (int i = 0; i < data.length(); i++) {
            JSONObject entity = data.getJSONObject(i);
            Post post = new Post();
//            System.out.print(entity.getInt("userId"));
//            System.out.print(entity.getInt("id"));
//            System.out.print(entity.getString("title"));
//            System.out.println(entity.getString("body"));
            post.setUserId(entity.getInt("userId"));
            post.setId(entity.getInt("id"));
            post.setTitle(entity.getString("title"));
            post.setBody(entity.getString("body"));

            System.out.println(post);
        }

    }

//    public static List<Post> advancedParse(HttpResponse<String> response) throws JsonProcessingException {
//        JsonMapper jsonMapper = new JsonMapper();
//        Post[] posts = jsonMapper.readValue(response.body(), Post[].class);
//        return List.of(posts);
//    }

    public static List<User> advancedParse(HttpResponse<String> response) throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        User[] users = jsonMapper.readValue(response.body(), User[].class);
        return List.of(users);
    }

}
