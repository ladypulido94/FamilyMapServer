package Handlers;

import DAO.DataAccessException;
import Request.LoginRequest;
import Result.*;
import Services.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = true;
        Gson gson = new Gson();

        try{

            if(exchange.getRequestMethod().equals("POST")){

                InputStream inputStream = exchange.getRequestBody();
                String test = helper.readString(inputStream);
                Object obj = gson.fromJson(test, LoginRequest.class);
                LoginRequest loginRequest = (LoginRequest)obj;
                LoginService loginService = new LoginService();
                LoginResult loginResult = loginService.login(loginRequest);

                if(loginResult == null){
                    success = false;
                } else{

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
                MainResult loginResult = new MainResult("Invalid Request.", false);
                String error = gson.toJson(loginResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }

        } catch (IOException | DataAccessException e){

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult loginResult = new MainResult("error " + e.getMessage(), false);
            String error = gson.toJson(loginResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
