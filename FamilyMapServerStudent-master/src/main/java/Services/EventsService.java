package Services;

import DAO.*;
import Model.Events;
import Result.EventResult;
import Result.EventsResult;

import java.sql.Connection;
import java.util.ArrayList;

public class EventsService {

    /**
     * Returns the event related to the id
     * @param eventID
     * @return
     */
    public EventResult find(String eventID) throws DataAccessException {

        Database db = new Database();
        Connection conn = null;
        EventResult eventResult = null;
        Events event = null;

        try{

            conn = db.openConnection();
            EventsDAO eDAO = new EventsDAO(conn);
            event = eDAO.find(eventID);
            db.closeConnection(true);
        } catch (DataAccessException e){

            db.closeConnection(false);
            e.printStackTrace();
        }

        eventResult = new EventResult(event);
        return eventResult;
    }

    /**
     * This method will return all the events from all the family members of the user
     * @param userName
     * @return
     */
    public EventsResult findAll(String userName) throws DataAccessException {

        Database db = new Database();
        Connection conn = null;
        EventsResult eventResult = null;
        ArrayList<Events> events = null;

        try{

            conn = db.openConnection();
            EventsDAO eDAO = new EventsDAO(conn);
            events = eDAO.findAll(userName);
            db.closeConnection(true);

        } catch (DataAccessException e){

            db.closeConnection(false);
            e.printStackTrace();
        }

        eventResult = new EventsResult(events);
        return eventResult;
    }
}
