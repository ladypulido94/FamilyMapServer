package Services;

import DAO.*;
import Model.*;
import Request.LoginRequest;
import Result.LoginResult;

import java.sql.Connection;

public class LoginService {

    /**
     * This will log in the user and return an authtoken.
     * @param loginRequest
     * @return
     */
    public LoginResult login (LoginRequest loginRequest) throws DataAccessException {

        Database db = new Database();
        Connection conn = null;
        LoginResult loginResult = null;

        try{
            conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);
            User user = uDAO.find(loginRequest.getUsername());

            if(user == null){
                throw new DataAccessException("User not found.");
            }

            if(!user.getPassword().equals(loginRequest.getPassword())){
                throw new DataAccessException("Incorrect password.");
            }

            AuthToken authToken = new AuthToken(user.getUsername());
            AuthDAO aDAO = new AuthDAO(conn);
            aDAO.insert(authToken);

            loginResult = new LoginResult(user.getUsername(), authToken.getToken(), user.getPersonID());

            db.closeConnection(true);
        } catch (DataAccessException e){
            db.closeConnection(false);
            throw new DataAccessException(e.toString());
        }

        return loginResult;
    }
}
