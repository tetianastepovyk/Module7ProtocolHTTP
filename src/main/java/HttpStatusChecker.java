import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class HttpStatusChecker {

    private HttpResponse<String> response = null;

    public String getStatusImage(int code) {
        String url = "https://http.cat/" + code + ".jpg";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return url;
            } else {
                throw new IOException("Failed to retrieve image. Response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("\n There is not image for HTTP status " + code);
        }
        return null;
    }
}
