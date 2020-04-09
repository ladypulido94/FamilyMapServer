package Services;

import DAO.*;
import Model.*;
import Request.*;
import Result.LoginResult;

import java.sql.Connection;
import java.util.UUID;

public class RegisterService {

    /**
     * THis method will create a new user, generate 4 generations of the user data,
     * log in the user and return an authtoken
     * @param registerRequest
     * @return
     */
    public static LoginResult register(RegisterRequest registerRequest) throws DataAccessException {

        LoginResult loginResult;
        Database db = new Database();
        Person person = null;
        User user = null;

        try {

            if(registerRequest.getUsername().equals(null) || registerRequest.getPassword().equals(null) || registerRequest.getEmail().equals(null)|| registerRequest.getFirstName().equals(null) ||
            registerRequest.getLastName().equals(null) || (!registerRequest.getGender().equals("f") && !registerRequest.getGender().equals("m"))){
                throw new DataAccessException("Invalid input.");
            }

            if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty() || registerRequest.getEmail().isEmpty() || registerRequest.getFirstName().isEmpty() ||
            registerRequest.getLastName().isEmpty() || registerRequest.getGender().isEmpty()){
                throw new DataAccessException("Invalid input.");
            }

            String personID = UUID.randomUUID().toString().substring(0, 8);
            person = new Person(personID, registerRequest.getUsername(), registerRequest.getFirstName(), registerRequest.getLastName(),
                    registerRequest.getGender().toUpperCase(), "", "", "");

            user = new User(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getFirstName(),
                    registerRequest.getLastName(), registerRequest.getGender().toUpperCase(), personID);

            Connection conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);
            PersonDAO pDAO = new PersonDAO(conn);
            uDAO.insertUser(user);
            pDAO.insertPerson(person);
            db.closeConnection(true);

            FillRequest fillRequest = new FillRequest(user.getUsername(), 4);
            FillService fillService = new FillService();
            fillService.fill(fillRequest);

        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            throw e;
        }

        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginService loginService = new LoginService();
        loginResult = loginService.login(loginRequest);

        return loginResult;
    }
}
