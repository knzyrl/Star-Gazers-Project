package helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.NearEarthObjectEntity;

/**
 * Parses raw JSON data from NASA's NeoWs API into a list of {@link NearEarthObjectEntity} objects.
 * Handles the JSON structure and converts relevant fields into entity objects.
 */
public class NearEarthObjectJsonParser {

    /**
     * Parses raw JSON data into a list of {@link NearEarthObjectEntity} objects.
     *
     * @param rawJson The raw JSON string from NASA's NeoWs API.
     * @return A list of {@link NearEarthObjectEntity} objects.
     * @throws IllegalArgumentException If the JSON parsing fails.
     */
    public static List<NearEarthObjectEntity> parse(String rawJson) {
        final List<NearEarthObjectEntity> neoEntities = new ArrayList<>();

        try {

            final JSONObject root = new JSONObject(rawJson);
            final JSONObject nearEarthObjects = root.getJSONObject("near_earth_objects");

            for (String date : nearEarthObjects.keySet()) {
                final JSONArray asteroids = nearEarthObjects.getJSONArray(date);

                for (int i = 0; i < asteroids.length(); i++) {
                    final JSONObject asteroid = asteroids.getJSONObject(i);

                    final String name = asteroid.getString("name");
                    final JSONArray closeApproachData = asteroid.getJSONArray("close_approach_data");
                    final JSONObject firstCloseApproach = closeApproachData.getJSONObject(0);
                    final String closestApproachDate = firstCloseApproach.getString("close_approach_date");
                    final double closestDistanceKm = firstCloseApproach.getJSONObject("miss_distance")
                            .getDouble("kilometers");

                    neoEntities.add(new NearEarthObjectEntity(name, closestApproachDate, closestDistanceKm));
                }
            }

        }
        catch (JSONException jsonException) {
            throw new IllegalArgumentException(
                    "Error parsing JSON data for Near Earth Objects: " + jsonException.getMessage(),
                    jsonException
            );
        }

        return neoEntities;
    }
}
