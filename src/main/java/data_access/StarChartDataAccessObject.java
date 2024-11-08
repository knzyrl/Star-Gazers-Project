package data_access;

import kong.unirest.core.Unirest;

import kong.unirest.core.HttpResponse;

public class StarChartDataAccessObject extends AstronomyAPIDataAccessObject {

    @Override
    public String executeQuery(String query) {
        HttpResponse<String> response = Unirest.post("https://api.astronomyapi.com/api/v2/studio/star-chart")
                .header("Authorization", String.format("Basic %s", AUTH_STRING))
                .body(query)
                .asString();
        return response.getBody().substring(21, response.getBody().length()-3);
    }

    public static void main(String[] args) {
        StarChartDataAccessObject dao = new StarChartDataAccessObject();
        System.out.println(dao.executeQuery("{\"style\":\"inverted\",\"observer\":{\"latitude\":33.775867,\"longitude\":-84.39733,\"date\":\"2024-11-04\"},\"view\":{\"type\":\"area\",\"parameters\":{\"position\":{\"equatorial\":{\"rightAscension\":0,\"declination\":0}},\"zoom\":6}}}"));
    }
}
