package DAO;

import Model.Places;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FilesDAO {
    public static final ArrayList<String> maleNames;
    public static final ArrayList<String> femaleNames;
    public static final ArrayList<String> lastNames;
    public static final ArrayList<Places> places;

    static {
        maleNames = readNames("src/main/files/mnames.json");
        femaleNames = readNames("src/main/files/fnames.json");
        lastNames = readNames("src/main/files/snames.json");
        places = readLocations("src/main/files/locations.json");
    }

    private static ArrayList<String> readNames(String path) {

        ArrayList<String> names = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            String jsonData = scanner.useDelimiter("\\A").next();

            //Parse into Json array
            JSONArray jsonArray = new JSONArray(jsonData);

            //Convert to array list
            for (int i = 0; i < jsonArray.length(); i++) {
                names.add(jsonArray.getString(i));
            }

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return names;
    }

    /** readLocations parses locations from JSON files
     * @return locations An array list of locations
     */
    private static ArrayList<Places> readLocations(String path) {

        ArrayList<Places> locations = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            String jsonData = scanner.useDelimiter("\\A").next();

            //Parse into Json array
            JSONArray jsonArray = new JSONArray(jsonData);

            //Convert to array list
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject l = jsonArray.getJSONObject(i);
                locations.add(new Places(l.getString("country"), l.getString("city"),
                        l.getDouble("latitude"), l.getDouble("longitude")));
            }

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return locations;
    }
}
