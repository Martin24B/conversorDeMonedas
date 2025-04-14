package conversordemonedas.api.connection;

import java.net.http.*;

import java.net.URI;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

import conversordemonedas.api.exceptions.ConnectionError;

public class Client {
    private HttpClient client;
    private String body;
    private String url;

    public Client () {
        this.client = buildClient();
        this.url = this.getUrl();
        this.body = "";
    }

    private static HttpClient buildClient() {
        return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NEVER)
            .connectTimeout(Duration.ofSeconds(Configuration.CONNECTION_TIMEOUT))
            .build();
    }

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (JavaHttpClient)")
                .timeout(Duration.ofSeconds(Configuration.RESPONSE_TIMEOUT))
                .GET()
                .build();
    }

    public void sendRequest(String endpoint) {
        try {
        	this.url = this.getUrl() + endpoint;
            HttpResponse<String> response = client.send(this.buildRequest(), HttpResponse.BodyHandlers.ofString());
            ConnectionError.getStatusCodeMessage(response.statusCode());
            this.setBody(response.body());
        } catch (UnknownHostException e) {     
        	ConnectionError.unknownHostError(e.getMessage());
        } catch (HttpConnectTimeoutException e) {
        	ConnectionError.connectionError(e.getMessage());
        } catch (HttpTimeoutException e) {     
        	ConnectionError.httpTimeoutError(e.getMessage());
        } catch (IOException e) {
        	ConnectionError.ioError(e.getMessage());
        } catch (InterruptedException e) {
        	ConnectionError.interruptionError(e.getMessage());
        } catch (Exception e) {  
        	ConnectionError.generalError(e.getMessage()); 
        }
    }

    private void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return Configuration.HOST_SERVER + Configuration.API_KEY;
    }
}
