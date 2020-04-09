package Services;

import DAO.*;
import Result.MainResult;

import java.sql.Connection;


public class ClearService {

    /**
     * This method will delete everything from the database. ALl the information stored in the user, event, person and auth table will be removed
     * @return
     */
    public MainResult clear() throws DataAccessException {
        Database db = new Database();
        MainResult result;

        Connection conn = db.openConnection();
        db.clearTables(conn);
        db.closeConnection(true);

        result = new MainResult("clear succeeded", true);

        return result;
    }
}
