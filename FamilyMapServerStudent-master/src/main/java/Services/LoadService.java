package Services;

import DAO.*;
import Model.*;
import Request.LoadRequest;
import Result.MainResult;

import java.sql.Connection;
import java.util.ArrayList;


public class LoadService {


    public MainResult load(LoadRequest request) throws DataAccessException {

        ClearService clearService = new ClearService();
        clearService.clear();

        ArrayList<User> users = request.getUsers();
        ArrayList<Person> persons = request.getPersons();
        ArrayList<Events> events = request.getEvents();

        Database db = new Database();

        try {

            Connection conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);
            PersonDAO pDAO = new PersonDAO(conn);
            EventsDAO eDAO = new EventsDAO(conn);

            for (int i = 0; i < users.size(); i++){
                uDAO.insertUser(users.get(i));
            }

            for (int i = 0; i < persons.size(); i++){
                pDAO.insertPerson(persons.get(i));
            }

            for (int i = 0; i < events.size(); i++){
                eDAO.insertEvent(events.get(i));
            }

            db.closeConnection(true);

        } catch (DataAccessException e){
            db.closeConnection(false);
            e.printStackTrace();
        }

        return new MainResult("Successfully added " + users.size() + " users, " + persons.size() + " persons, and " + events.size() + " events to the database.", true);

    }
}
