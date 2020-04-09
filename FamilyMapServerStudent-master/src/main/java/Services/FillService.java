package Services;

import DAO.*;
import Model.*;
import Request.FillRequest;
import Result.MainResult;

import java.sql.*;
import java.util.*;

public class FillService {

    private final ArrayList<String> maleNames = FilesDAO.maleNames;
    private final ArrayList<String> femaleNames = FilesDAO.femaleNames;
    private final ArrayList<String> lastNames = FilesDAO.lastNames;
    private final ArrayList<Places> locations  = FilesDAO.places;
    int numPersons;
    int numEvents;

    /**
     *This method will insert the data of the user in the database. If there is already data associated with the current username,
     * it will be deleted.
     * @param fillRequest
     * @return
     */
    public MainResult fill(FillRequest fillRequest) throws DataAccessException {

        numPersons = 0;
        numEvents = 0;

        deleteUsernameData(fillRequest.getUsername());

        Database db = new Database();
        Connection conn = db.openConnection();
        UserDAO uDAO = new UserDAO(conn);
        User user = uDAO.find(fillRequest.getUsername());
        db.closeConnection(true);

        if (user == null) {

            throw new DataAccessException(fillRequest.getUsername() + "is not a user.");
        }

        Person person = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getGender(), null, null, null);

        //Birth Generator
        String birthID = UUID.randomUUID().toString().substring(0, 8);
        Double Latitude = getRandomLocation().getLatitude();
        Double Longitude = getRandomLocation().getLongitude();
        String Country = getRandomLocation().getCountry();
        String City = getRandomLocation().getCity();
        int birthYear = 1994;
        Events birth = new Events(birthID, person.getUsername(), person.getPersonID(), Latitude, Longitude,
                Country, City, "Birth", birthYear);

        conn = db.openConnection();
        try {

            PersonDAO pDAO = new PersonDAO(conn);
            pDAO.insertPerson(person);
            numPersons++;

            EventsDAO eDAO = new EventsDAO(conn);
            eDAO.insertEvent(birth);
            numEvents++;

            db.closeConnection(true);
        } catch (DataAccessException e) {

            db.closeConnection(false);
            e.printStackTrace();
        }

        //Family Generator dates
        familyGenerator(person, birthYear, fillRequest.getNumGenerations());

        return new MainResult("Successfully added " + numPersons + " persons and " +
                numEvents + " events.", true);
    }

    private void deleteUsernameData(String userName) throws DataAccessException {

        Database db = new Database();
        Connection conn = db.openConnection();
        String sql = "DELETE FROM Events WHERE AssociatedUsername = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.executeUpdate();
            db.closeConnection(true);

        } catch (SQLException | DataAccessException e) {

            db.closeConnection(false);
            throw new DataAccessException(e.getMessage());
        }

        conn = db.openConnection();
        sql = "DELETE FROM Persons WHERE AssociatedUsername = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.executeUpdate();
            db.closeConnection(true);
        } catch (SQLException | DataAccessException e) {
            db.closeConnection(false);
            throw new DataAccessException(e.getMessage());
        }

    }

    private void familyGenerator (Person person, int personBirthYear, int numGenerations) throws DataAccessException {

        if (numGenerations == 0) {
            return;
        }

        //Father and Mother generator
        String fatherID = UUID.randomUUID().toString().substring(0, 8);
        String fatherFirstName = getRandomName(maleNames);
        String fatherLastName = person.getLastName();
        Person father = new Person(fatherID, person.getUsername(), fatherFirstName, fatherLastName,
                "M", "", "", "");

        String motherID = UUID.randomUUID().toString().substring(0, 8);
        String motherFirstName = getRandomName(femaleNames);
        String motherLastName = getRandomName(lastNames);
        Person mother = new Person(motherID, person.getUsername(), motherFirstName, motherLastName,
                "F", "", "", "");
        father.setSpouseID(motherID);
        mother.setSpouseID(fatherID);

        //Father and Mother events generator
        String fatherBirthID = UUID.randomUUID().toString().substring(0, 8);
        Places Location = getRandomLocation();
        Double Latitude = Location.getLatitude();
        Double Longitude = Location.getLongitude();
        String Country = Location.getCountry();
        String City = Location.getCity();
        int birthYear = personBirthYear - 40;

        //Father Events
        Events fatherBirth = new Events(fatherBirthID, person.getUsername(), fatherID, Latitude, Longitude,
                Country, City, "Birth", birthYear);

        String fatherMarriageID = UUID.randomUUID().toString().substring(0, 8);
        int marriageYear = birthYear + 25;
        Events fatherMarriage = new Events(fatherMarriageID, person.getUsername(), fatherID, Latitude, Longitude,
                Country, City, "Marriage", marriageYear);

        String fatherDeathID = UUID.randomUUID().toString().substring(0, 8);
        int fatherDeathYear = birthYear + 75;
        Events fatherDeath = new Events(fatherDeathID, person.getUsername(), fatherID, Latitude, Longitude,
                Country, City, "Death", fatherDeathYear);

        //Mother Events
        String motherBirthID = UUID.randomUUID().toString().substring(0, 8);
        Events motherBirth = new Events(motherBirthID, person.getUsername(), motherID, Latitude, Longitude,
                Country, City, "Birth", birthYear);

        String motherMarriageID = UUID.randomUUID().toString().substring(0, 8);
        Events motherMarriage = new Events(motherMarriageID, person.getUsername(), motherID, Latitude, Longitude,
               Country, City, "Marriage", marriageYear);

        String motherDeathID = UUID.randomUUID().toString().substring(0, 8);
        int motherDeathYear = birthYear + 80;
        Events motherDeath = new Events(motherDeathID, person.getUsername(), motherID, Latitude, Longitude,
                Country, City, "Death", motherDeathYear);

        person.setFatherID(fatherID);
        person.setMotherID(motherID);

        Database db = new Database();
        Connection conn = db.openConnection();

        try {

            PersonDAO pDAO = new PersonDAO(conn);
            pDAO.delete(person.getPersonID());
            pDAO.insertPerson(person);
            pDAO.insertPerson(father);
            pDAO.insertPerson(mother);
            numPersons = numPersons + 2;

            EventsDAO eDAO = new EventsDAO(conn);
            eDAO.insertEvent(fatherBirth);
            eDAO.insertEvent(fatherMarriage);
            eDAO.insertEvent(fatherDeath);
            eDAO.insertEvent(motherBirth);
            eDAO.insertEvent(motherMarriage);
            eDAO.insertEvent(motherDeath);
            numEvents = numEvents + 6;

            db.closeConnection(true);
        } catch (DataAccessException e) {

            db.closeConnection(false);
            e.printStackTrace();
        }

        familyGenerator(father, birthYear, numGenerations - 1);
        familyGenerator(mother, birthYear, numGenerations - 1);
    }

    private String getRandomName(ArrayList<String> names) {

        Random rand = new Random();
        int randLocationIndex = rand.nextInt(names.size());
        return names.get(randLocationIndex);
    }

    public Places getRandomLocation() {

        Random random = new Random();
        int index = random.nextInt(locations.size());
        return locations.get(index);

    }

}
