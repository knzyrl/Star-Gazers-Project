package data_access;

import java.util.Base64;

import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;

/**
 * Data access object for retrieving events data from the Astronomy API.
 */
public class EventsDataAccessObject implements AstronomyApiDataAccessObject {
    @Override
    public JSONObject executeQuery(String query) {
        final String appId = "44f82562-233e-4f52-af5d-ca47ea0decc6";
        final String appSecret = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5ff34eec"
                + "5bcf8a9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c25015d0a67653f07de92d3577c82a6a44a"
                + "f9e051eb2aa8c30f0fac00887d322d3992a1125a6b630bc94e3e122cf28d6b";
        final String authString = Base64.getEncoder().encodeToString((appId + ":" + appSecret).getBytes());

        final JSONObject response = Unirest.get(query)
                .header("Authorization", String.format("Basic %s", authString))
                .asJson()
                .getBody()
                .getObject().getJSONObject("data").getJSONObject("table");
        return response;
    }

    /**
     * Main method for testing the EventsDataAccessObject functionality.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        final EventsDataAccessObject dao = new EventsDataAccessObject();
        System.out.println(dao.executeQuery("https://api.astronomyapi.com/api/v2/bodies/events/moon?longitude="
                + "-84.39733&latitude=33.775867&elevation=1&from_date=2020-12-20&to_date=2022-12-"
                + "23&time=00%3A00%3A00"));
    }
}
