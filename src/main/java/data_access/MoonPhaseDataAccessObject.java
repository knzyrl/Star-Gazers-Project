package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

public class MoonPhaseDataAccessObject extends AstronomyAPIDataAccessObject{
    @Override
    public String executeQuery(String query) {
        HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/moon-phase")
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .body(query)
                .asString();
        return response.getBody().substring(21, response.getBody().length()-3);
    }

    public static void main(String[] args) {
        MoonPhaseDataAccessObject moonDAO = new MoonPhaseDataAccessObject();
        System.out.println(moonDAO.executeQuery("{\"style\":{\"moonStyle\":\"default\",\"backgroundStyle\":\"stars\",\"backgroundColor\":\"#000000\",\"headingColor\":\"#ffffff\",\"textColor\":\"#ffffff\"},\"observer\":{\"latitude\":33.775867,\"longitude\":-84.39733,\"date\":\"2024-11-08\"},\"view\":{\"type\":\"portrait-simple\",\"parameters\":{}}}"));
    }
}
