package Handlers;

import DAO.DataAccessException;
import Request.FillRequest;
import Result.MainResult;
import Services.FillService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        Gson gson = new Gson();

        try{
            if(exchange.getRequestMethod().equals("POST")){

                String path = exchange.getRequestURI().getPath();
                String[] URI = path.split("/");
                String userName = URI[2];

                int generations;

                try{

                    generations = Integer.parseInt(URI[URI.length - 1]);

                }catch (Exception e){
                    generations = 4;
                }

                FillRequest fillRequest = new FillRequest(userName, generations);
                FillService fillService = new FillService();
                MainResult fillResult = fillService.fill(fillRequest);

                String json = gson.toJson(fillResult);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                OutputStream outBody = exchange.getResponseBody();
                helper.writeString(json, outBody);
                outBody.close();
                success = true;
            }

            if(!success){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                OutputStream outputStream = exchange.getResponseBody();
                MainResult fillResult = new MainResult("Invalid Request.", false);
                String error = gson.toJson(fillResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }

        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult fillResult = new MainResult("Error: " + e.getMessage(), false);
            String error = gson.toJson(fillResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
