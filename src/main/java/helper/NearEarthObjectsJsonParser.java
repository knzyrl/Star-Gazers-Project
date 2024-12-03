package helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.NearEarthObjectEntity;

/**
 * Class to parse through raw JSON data for Near Earth Objects use case.
 */

public class NearEarthObjectsJsonParser {

    /**
     * Parses raw JSON data into a list of NearEarthObjectEntity objects.
     *
     * @param rawJson The raw JSON string from NASA's NeoWs API.
     * @return A list of NearEarthObjectEntity objects.
     * @throws IllegalArgumentException If the JSON parsing fails.
     */
    public static List<NearEarthObjectEntity> parse(String rawJson) {
        if (rawJson == null || rawJson.isEmpty()) {
            throw new IllegalArgumentException("Input JSON string is null or empty.");
        }

        final List<NearEarthObjectEntity> neoEntities = new ArrayList<>();

        try {
            final JSONObject root = new JSONObject(rawJson);
            final JSONObject nearEarthObjects = root.optJSONObject("near_earth_objects");

            if (nearEarthObjects == null) {
                // Return an empty list instead of throwing an exception
                return neoEntities;
            }

            for (String date : nearEarthObjects.keySet()) {
                final JSONArray asteroids = nearEarthObjects.optJSONArray(date);
                if (asteroids == null) {
                    continue;
                }

                for (int i = 0; i < asteroids.length(); i++) {
                    final JSONObject asteroid = asteroids.optJSONObject(i);
                    if (asteroid == null) {
                        continue;
                    }

                    final String name = asteroid.optString("name", "Unknown");
                    final JSONArray closeApproachData = asteroid.optJSONArray("close_approach_data");

                    if (closeApproachData != null && !closeApproachData.isEmpty()) {
                        final JSONObject firstCloseApproach = closeApproachData.optJSONObject(0);
                        if (firstCloseApproach != null) {
                            final String closestApproachDate = firstCloseApproach.optString("close_approach_date",
                                    "Unknown");
                            final double closestDistanceKm = firstCloseApproach.optJSONObject("miss_distance")
                                    .optDouble("kilometers", -1);
                            final double relativeVelocity = firstCloseApproach.optJSONObject("relative_velocity")
                                    .optDouble("kilometers_per_hour", -1);

                            neoEntities.add(new NearEarthObjectEntity(name, closestApproachDate, closestDistanceKm,
                                    relativeVelocity));
                        }
                    }
                    else {

                        neoEntities.add(new NearEarthObjectEntity(name, "Unknown",
                                -1, -1));
                    }
                }
            }
        }
        catch (JSONException exception) {
            throw new IllegalArgumentException("Error parsing JSON data for Near Earth Objects: "
                    + exception.getMessage(), exception);
        }

        return neoEntities;
    }

}
