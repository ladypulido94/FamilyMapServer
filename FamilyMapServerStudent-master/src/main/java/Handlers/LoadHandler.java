package Handlers;

import DAO.DataAccessException;
import Request.LoadRequest;
import Result.MainResult;
import Services.LoadService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;
        Gson gson = new Gson();

        try{
            if(exchange.getRequestMethod().equals("POST")){

                InputStream inputStream = exchange.getRequestBody();
                String test = helper.readString(inputStream);
                Object obj = gson.fromJson(test,LoadRequest.class);
                LoadRequest load = (LoadRequest) obj;
                LoadService loadService = new LoadService();
                MainResult loadResult = loadService.load(load);

                String json = gson.toJson(loadResult);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                OutputStream outBody = exchange.getResponseBody();
                helper.writeString(json, outBody);
                outBody.close();
                success = true;
            }

            if(success == false){

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                OutputStream outputStream = exchange.getResponseBody();
                MainResult loadResult = new MainResult("Load failed due an internal error", false);
                String error = gson.toJson(loadResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }

        } catch (IOException | DataAccessException e){

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult clearResult = new MainResult("Load failed due an internal error", false);
            String error = gson.toJson(clearResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
