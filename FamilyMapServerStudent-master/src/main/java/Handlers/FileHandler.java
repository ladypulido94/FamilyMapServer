package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try{
            String filepath;
            String urlPath = exchange.getRequestURI().toString();

            if(urlPath.equals("/")){
                filepath = "/Users/sysop/Documents/FamilyMapServerStudent-master/web/index.html";
            } else{
                filepath = "/Users/sysop/Documents/FamilyMapServerStudent-master/web" + urlPath;
            }

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();
            Path filePath = FileSystems.getDefault().getPath(filepath);

            Files.copy(filePath, respBody);
            respBody.close();

        } catch (IOException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
