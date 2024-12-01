package data_access;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

import java.util.Base64;

public class MoonPhaseDataAccessObject implements AstronomyAPIDataAccessObject{
    @Override
    public String executeQuery(String query) {
        final String APP_ID = "44f82562-233e-4f52-af5d-ca47ea0decc6";
        final String APP_SECRET = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5ff34eec5bcf8a9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c25015d0a67653f07de92d3577c82a6a44af9e051eb2aa8c30f0fac00887d322d3992a1125a6b630bc94e3e122cf28d6b";
        final String AUTH_STRING = Base64.getEncoder().encodeToString((APP_ID + ":" + APP_SECRET).getBytes());

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
