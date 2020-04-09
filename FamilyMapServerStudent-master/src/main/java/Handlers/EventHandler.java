package Handlers;

import DAO.*;
import Result.*;
import Services.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = true;
        Gson gson = new Gson();

        try {
            if(exchange.getRequestMethod().equals("GET")){
                boolean test = false;
                String rsJson;

                String path = exchange.getRequestURI().getPath();
                String[] URI = path.split("/");
                String eventID = null;

                try{
                    eventID = URI[2];
                } catch (Exception e){
                    test = true;
                }

                String headers = exchange.getRequestHeaders().getFirst("Authorization");

                if(headers == null){
                    success = false;
                }
                else {
                    String userName = helper.authentication(headers);

                    if(!test){

                        EventsService eventsService = new EventsService();
                        EventResult eventResult = eventsService.find(eventID);

                        if(!userName.equals(eventResult.getAssociatedUsername())){
                            throw new DataAccessException("Event not related to  " + userName);
                        }
                        rsJson = gson.toJson(eventResult);

                    } else {
                        EventsService eventsService = new EventsService();
                        EventsResult eventsResult = eventsService.findAll(userName);
                        rsJson = gson.toJson(eventsResult);
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream outBody = exchange.getResponseBody();
                    helper.writeString(rsJson, outBody);
                    outBody.close();
                    success = true;

                }

                if(!success){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                    OutputStream outputStream = exchange.getResponseBody();
                    MainResult eventResult = new MainResult("Error", false);
                    String error = gson.toJson(eventResult);
                    helper.writeString(error, outputStream);
                    outputStream.close();
                }
            }

        } catch (DataAccessException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult eventResult = new MainResult("Error: " + e.getMessage(), false);
            String error = gson.toJson(eventResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
