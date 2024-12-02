package helper;

import entity.NearEarthObjectEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NEOJsonParser {

    /**
     * Parses raw JSON data into a list of NearEarthObjectEntity objects.
     *
     * @param rawJson The raw JSON string from NASA's NeoWs API.
     * @return A list of NearEarthObjectEntity objects.
     */
    public static List<NearEarthObjectEntity> parse(String rawJson) {
        List<NearEarthObjectEntity> neoEntities = new ArrayList<>();

        try {

            JSONObject root = new JSONObject(rawJson);
            JSONObject nearEarthObjects = root.getJSONObject("near_earth_objects");

            for (String date : nearEarthObjects.keySet()) {
                JSONArray asteroids = nearEarthObjects.getJSONArray(date);

                for (int i = 0; i < asteroids.length(); i++) {
                    JSONObject asteroid = asteroids.getJSONObject(i);

                    String name = asteroid.getString("name");
                    JSONArray closeApproachData = asteroid.getJSONArray("close_approach_data");
                    JSONObject firstCloseApproach = closeApproachData.getJSONObject(0);
                    String closestApproachDate = firstCloseApproach.getString("close_approach_date");
                    double closestDistanceKm = firstCloseApproach.getJSONObject("miss_distance").getDouble("kilometers");
                    double relativeVelocity = firstCloseApproach.getJSONObject("relative_velocity").getDouble("kilometers_per_hour");

                    neoEntities.add(new NearEarthObjectEntity(name, closestApproachDate, closestDistanceKm, relativeVelocity));
                }
            }

        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }

        return neoEntities;
    }
}
