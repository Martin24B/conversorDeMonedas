package conversordemonedas.api.connection;

import java.net.URI;
import java.net.http.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

import conversordemonedas.api.exceptions.ConnectionError;

public class ApiClient {
    private final HttpClient client;
    private String responseBody;

    public ApiClient() {
        this.client = buildClient();
        this.responseBody = "";
    }

    private static HttpClient buildClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(Config.CONNECTION_TIMEOUT))
                .build();
    }

    private HttpRequest buildRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (JavaHttpClient)")
                .timeout(Duration.ofSeconds(Config.RESPONSE_TIMEOUT))
                .GET()
                .build();
    }

    public void sendRequest(String endpoint) {
        String fullUrl = buildBaseUrl() + endpoint;

        try {
            HttpRequest request = buildRequest(fullUrl);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ConnectionError.getStatusCodeMessage(response.statusCode());
            this.responseBody = response.body();

        } catch (UnknownHostException e) {
            ConnectionError.unknownHostError(e.getMessage());
        } catch (HttpConnectTimeoutException e) {
            ConnectionError.connectionError(e.getMessage());
        } catch (HttpTimeoutException e) {
            ConnectionError.httpTimeoutError(e.getMessage());
        } catch (IOException e) {
            ConnectionError.ioError(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ConnectionError.interruptionError(e.getMessage());
        } catch (Exception e) {
            ConnectionError.generalError(e.getMessage());
        }
    }

    private String buildBaseUrl() {
        return Config.HOST_SERVER + Config.API_KEY;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
