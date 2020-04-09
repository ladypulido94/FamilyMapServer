package Handlers;

import DAO.DataAccessException;
import Result.MainResult;
import Services.ClearService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;
        Gson gson = new Gson();

        try{
            if(exchange.getRequestMethod().equals("POST")){
                ClearService clearService = new ClearService();
                MainResult clearResult = clearService.clear();
                String json = gson.toJson(clearResult);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                OutputStream outBody = exchange.getResponseBody();
                helper.writeString(json, outBody);
                outBody.close();
                success = true;

            }

            if(!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                OutputStream outputStream = exchange.getResponseBody();
                MainResult clearResult = new MainResult("error", false);
                String error = gson.toJson(clearResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }

        } catch (IOException | DataAccessException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult clearResult = new MainResult("Clear failed due an internal error", false);
            String error = gson.toJson(clearResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }

}
