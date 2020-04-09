package Result;

import Model.Person;

import java.util.ArrayList;

public class PersonsResult {

    private ArrayList<Person> persons;

    private String message;
    private boolean success;

    /**
     * This will store all the persons from the persons services
     * @param persons
     */
    public PersonsResult(ArrayList<Person> persons) {

        this.persons = persons;
        this.success = true;
    }

    public PersonsResult(String message){
        this.message = message;
        this.success = false;
    }

    /**
     * This will return all the persons to the client
     * @return
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * This will store the value of the array of persons from the persons services
     * @param persons
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
