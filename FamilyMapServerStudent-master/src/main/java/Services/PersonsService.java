package Services;

import DAO.*;
import Model.Person;
import Result.PersonResult;
import Result.PersonsResult;

import java.sql.Connection;
import java.util.ArrayList;

public class PersonsService {

    public PersonResult find (String personID) throws DataAccessException {

        Database db = new Database();
        Connection conn = null;
        PersonResult personResult = null;
        Person person = null;

        try{

            conn = db.openConnection();
            PersonDAO pDAO = new PersonDAO(conn);
            person = pDAO.findPerson(personID);
            db.closeConnection(true);

        } catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
        }

        personResult = new PersonResult(person);
        return personResult;
    }

    /**
     * This method will return all the family members from the user
     * @param userName
     * @return
     */
    public PersonsResult findAll(String userName) throws DataAccessException{

        Database db = new Database();
        Connection conn = null;
        PersonsResult personsResult = null;
        ArrayList<Person> persons = null;

        try{
            conn = db.openConnection();
            PersonDAO pDAO = new PersonDAO(conn);
            persons = pDAO.findAll(userName);
            db.closeConnection(true);

        } catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
        }

        personsResult = new PersonsResult(persons);

        return personsResult;
    }
}
