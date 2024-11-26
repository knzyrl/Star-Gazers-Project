package data_access;

import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;

public class EventsDataAccessObject extends AstronomyAPIDataAccessObject {
    @Override
    public JSONObject executeQuery(String query) {
        JSONObject response = Unirest.get(query)
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .asJson()
                .getBody()
                .getObject().getJSONObject("data").getJSONObject("table");
        return response;
    }

    public static void main(String[] args) {
        EventsDataAccessObject dao = new EventsDataAccessObject();
        System.out.println(dao.executeQuery("https://api.astronomyapi.com/api/v2/bodies/events/moon?longitude=-84.39733&latitude=33.775867&elevation=1&from_date=2020-12-20&to_date=2022-12-23&time=00%3A00%3A00"));
    }
}
