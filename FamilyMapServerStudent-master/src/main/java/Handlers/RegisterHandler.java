package Handlers;

import DAO.DataAccessException;
import Request.RegisterRequest;
import Result.*;
import Services.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.logging.Logger;

public class RegisterHandler implements HttpHandler {

    private Logger logger;
    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = true;
        Gson gson = new Gson();

        try{
            if(exchange.getRequestMethod().equals("POST")){

                InputStream inputStream = exchange.getRequestBody();
                String test = helper.readString(inputStream);
                Object obj = gson.fromJson(test, RegisterRequest.class);
                RegisterRequest registerRequest = (RegisterRequest) obj;
                RegisterService registerService = new RegisterService();
                LoginResult loginResult = registerService.register(registerRequest);

                if(loginResult == null){
                    success = false;
                } else {
                    String json = gson.toJson(loginResult);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                    OutputStream outBody = exchange.getResponseBody();
                    helper.writeString(json, outBody);
                    outBody.close();
                    success = true;
                }
            }
            if(!success){

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                OutputStream outputStream = exchange.getResponseBody();
                MainResult registerResult = new MainResult("Invalid Request.", false);
                String error = gson.toJson(registerResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }
        } catch (IOException | DataAccessException e){

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult registerResult = new MainResult("Error: " + e.getMessage(), false);
            String error = gson.toJson(registerResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
