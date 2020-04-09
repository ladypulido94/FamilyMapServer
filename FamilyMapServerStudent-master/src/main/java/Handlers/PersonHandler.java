package Handlers;

import DAO.*;
import Model.User;
import Result.*;
import Services.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;

public class PersonHandler implements HttpHandler {

    HelpHandler helper = new HelpHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = true;
        Gson gson = new Gson();

        try{
            if(exchange.getRequestMethod().equals("GET")){

                boolean test = false;
                String rsJson = null;

                String path = exchange.getRequestURI().getPath();
                String[] URI = path.split("/");
                String personID = null;

                String headers = exchange.getRequestHeaders().getFirst("Authorization");

                if(headers == null){
                    success = false;
                } else {

                    String userName = helper.authentication(headers);

                    try{
                        personID = URI[2];
                        test = false;
                    }catch (Exception e){

                        e.printStackTrace();
                        test = true;
                    }

                    if(!test){

                        PersonsService personsService = new PersonsService();
                        PersonResult personResult = personsService.find(personID);
                        Database db = new Database();
                        Connection conn = db.openConnection();
                        UserDAO uDAO = new UserDAO(conn);
                        User user = uDAO.find(userName);
                        db.closeConnection(true);

                        if(!user.getUsername().equals(personResult.getAssociatedUsername())){

                            throw new DataAccessException("Person not related to " + userName);
                        }
                        rsJson = gson.toJson(personResult);

                    } else {

                        Database db = new Database();
                        Connection conn = db.openConnection();
                        UserDAO uDAO = new UserDAO(conn);
                        User user = null;

                        try {

                            user = uDAO.find(userName);
                            db.closeConnection(true);
                        } catch (DataAccessException e){

                            e.printStackTrace();
                            db.closeConnection(false);
                        }

                        PersonsService personsService = new PersonsService();
                        PersonsResult personsResult = personsService.findAll(user.getPersonID());
                        rsJson = gson.toJson(personsResult);
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream outBody = exchange.getResponseBody();
                    helper.writeString(rsJson, outBody);
                    outBody.close();
                    success = true;

                }

            }

            if(!success){

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                OutputStream outputStream = exchange.getResponseBody();
                MainResult eventResult = new MainResult("Error", false);
                String error = gson.toJson(eventResult);
                helper.writeString(error, outputStream);
                outputStream.close();
            }

        } catch (DataAccessException e){

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            OutputStream outputStream = exchange.getResponseBody();
            MainResult eventResult = new MainResult("error: " + e.getMessage(), false);
            String error = gson.toJson(eventResult);
            helper.writeString(error, outputStream);
            outputStream.close();
            e.printStackTrace();
        }
    }
}
