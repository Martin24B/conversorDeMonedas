package conversordemonedas.api.conexion;

import java.net.http.*;
import java.net.URI;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

import conversordemonedas.api.excepciones.ErrorDeConexion;

public class Cliente {
    private HttpClient client;
    private String body;
    private String url;

    public Cliente () {
        this.client = buildClient();
        this.url = Configuracion.HOST_SERVER + Configuracion.API_KEY;
        this.body = "";
    }

    private static HttpClient buildClient() {
        return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NEVER)
            .connectTimeout(Duration.ofSeconds(Configuracion.CONNECTION_TIMEOUT))
            .build();
    }

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (JavaHttpClient)")
                .timeout(Duration.ofSeconds(Configuracion.RESPONSE_TIMEOUT))
                .GET()
                .build();
    }

    public void sendRequest(String endpoint) {
        try {
        	this.url += endpoint;
            HttpResponse<String> response = client.send(this.buildRequest(), HttpResponse.BodyHandlers.ofString());
            ErrorDeConexion.getStatusCodeMessage(response.statusCode());
            this.setBody(response.body());
        } catch (UnknownHostException e) {     
        	ErrorDeConexion.unknownHostError(e.getMessage());
        } catch (HttpConnectTimeoutException e) {
        	ErrorDeConexion.connectionError(e.getMessage());
        } catch (HttpTimeoutException e) {     
        	ErrorDeConexion.httpTimeoutError(e.getMessage());
        } catch (IOException e) {
        	ErrorDeConexion.ioError(e.getMessage());
        } catch (InterruptedException e) {
        	ErrorDeConexion.interruptionError(e.getMessage());
        } catch (Exception e) {  
        	ErrorDeConexion.generalError(e.getMessage()); 
        }
    }

    private void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
