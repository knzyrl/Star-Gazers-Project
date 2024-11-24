package data_access;

import kong.unirest.core.Unirest;

import kong.unirest.core.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class GeocoderDataAccessObject extends GeocoderAPIDataAccessObject {

    public List<String> converter(String address){
        List<String> longlat = new ArrayList();

        String encodedAddress = "1600+Amphitheatre+Parkway+Mountain+View";
        String newAddress = address.replace("\\s", "+");


        String url = "https://geocode.maps.co/search?q=" + newAddress + "&api_key=" + APIkey;

        HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == 200) {
            String responseBody = response.getBody();

            String lat = null;
            String lon = null;

            // Find the position of "lat" and extract its value
            int latIndex = responseBody.indexOf("\"lat\":\"");
            if (latIndex != -1) {
                int latStart = latIndex + 7; // Length of "lat":" is 7
                int latEnd = responseBody.indexOf("\"", latStart);
                lat = responseBody.substring(latStart, latEnd);
            }

            // Find the position of "lon" and extract its value
            int lonIndex = responseBody.indexOf("\"lon\":\"");
            if (lonIndex != -1) {
                int lonStart = lonIndex + 7; // Length of "lon":" is 7
                int lonEnd = responseBody.indexOf("\"", lonStart);
                lon = responseBody.substring(lonStart, lonEnd);
            }

            longlat.add(lon);
            longlat.add(lat);

            return longlat;


        } else {
            return null;
        }

    }
    public static void main(String[] args) {
        String encodedAddress = "1600+Amphitheatre+Parkway+Mountain+View";

        String url = "https://geocode.maps.co/search?q=" + encodedAddress + "&api_key=" + APIkey;

        HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == 200) {
            String responseBody = response.getBody();

            String lat = null;
            String lon = null;

            // Find the position of "lat" and extract its value
            int latIndex = responseBody.indexOf("\"lat\":\"");
            if (latIndex != -1) {
                int latStart = latIndex + 7; // Length of "lat":" is 7
                int latEnd = responseBody.indexOf("\"", latStart);
                lat = responseBody.substring(latStart, latEnd);
            }

            // Find the position of "lon" and extract its value
            int lonIndex = responseBody.indexOf("\"lon\":\"");
            if (lonIndex != -1) {
                int lonStart = lonIndex + 7; // Length of "lon":" is 7
                int lonEnd = responseBody.indexOf("\"", lonStart);
                lon = responseBody.substring(lonStart, lonEnd);
            }

            System.out.println(lat + ", " + lon);

        } else {
            System.out.println("Failed: " + response.getStatus() + " " + response.getBody());
        }
    }
}
