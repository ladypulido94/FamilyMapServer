package Handlers;

import DAO.AuthDAO;
import DAO.DataAccessException;
import DAO.Database;
import Model.AuthToken;

import java.io.*;
import java.sql.Connection;

public class HelpHandler {

    public String readString(InputStream is) throws IOException {

        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);

        char[] buf = new char[1024];
        int len;

        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }

        return sb.toString();
    }

    public void writeString(String input, OutputStream output) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
        outputStreamWriter.write(input);
        outputStreamWriter.flush();
    }

    public String authentication(String token) throws DataAccessException {

        Database db = new Database();
        Connection conn = db.openConnection();
        String userName;

        try {
            AuthDAO aDao = new AuthDAO(conn);
            AuthToken authToken = aDao.find(token);
            userName = authToken.getUsername();
            db.closeConnection(true);

        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            throw e;
        }

        return userName;
    }
}
