package Request;

import Model.Events;
import Model.Person;
import Model.User;

import java.util.ArrayList;

public class LoadRequest {

    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Events> events;


    /**
     * This method will store the all the users, persons and events in different arrays from the client
     * @param users
     * @param persons
     * @param events
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Events> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }


    /**
     * This will return all the users to the Load service
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * This method will store the users values passed from the client
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * This will return all the persons to the load service
     * @return
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * This method will store the persons values passed from the client
     * @param persons
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * This will return all the events to the load service
     * @return
     */
    public ArrayList<Events> getEvents() {
        return events;
    }

    /**
     * This method will store the events values passed from the client
     * @param events
     */
    public void setEvents(ArrayList<Events> events) {
        this.events = events;
    }

}
