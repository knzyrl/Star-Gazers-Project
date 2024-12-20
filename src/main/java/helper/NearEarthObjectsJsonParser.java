package helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import use_case.near_earth_objects.NearEarthObjectsOutputData;

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
    public static List<NearEarthObjectsOutputData> parse(String rawJson) {
        final String unknownText = "Unknown";

        if (rawJson == null || rawJson.isEmpty()) {
            throw new IllegalArgumentException("Input JSON string is null or empty.");
        }

        final List<NearEarthObjectsOutputData> neoOutputData = new ArrayList<>();

        try {
            final JSONObject root = new JSONObject(rawJson);
            final JSONObject nearEarthObjects = root.optJSONObject("near_earth_objects");

            if (nearEarthObjects != null) {
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

                        final String name = asteroid.optString("name", unknownText);
                        final JSONArray closeApproachData = asteroid.optJSONArray("close_approach_data");

                        if (closeApproachData != null && !closeApproachData.isEmpty()) {
                            final JSONObject firstCloseApproach = closeApproachData.optJSONObject(0);
                            if (firstCloseApproach != null) {
                                final String closestApproachDate = firstCloseApproach.optString("close_approach_date",
                                        unknownText);
                                final double closestDistanceKm = firstCloseApproach.optJSONObject("miss_distance")
                                        .optDouble("kilometers", -1);
                                final double relativeVelocity = firstCloseApproach.optJSONObject("relative_velocity")
                                        .optDouble("kilometers_per_hour", -1);

                                neoOutputData.add(new NearEarthObjectsOutputData(name, closestApproachDate,
                                        closestDistanceKm, relativeVelocity));
                            }
                        }
                        else {
                            neoOutputData.add(new NearEarthObjectsOutputData(name, unknownText, -1, -1));
                        }
                    }
                }
            }
        }
        catch (JSONException exception) {
            throw new IllegalArgumentException("Error parsing JSON data for Near Earth Objects: "
                    + exception.getMessage(), exception);
        }

        return neoOutputData;
    }

}
