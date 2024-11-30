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
            String properAddress = null;

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

            // Find the full address of the location given
            int addressIndex = responseBody.indexOf("\"display_name\":\"");
            if (addressIndex != -1) {
                int addressStart = addressIndex + 16;
                int addressEnd = responseBody.indexOf("\"", addressStart);
                properAddress = responseBody.substring(addressStart, addressEnd);
            }

            longlat.add(lon);
            longlat.add(lat);
            longlat.add(properAddress);

            return longlat;


        } else {
            return null;
        }

    }
    public static void main(String[] args) {
        String encodedAddress = "CN+Tower";

        String url = "https://geocode.maps.co/search?q=" + encodedAddress + "&api_key=" + APIkey;

        HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == 200) {
            String responseBody = response.getBody();

            System.out.println(responseBody);

            String lat = null;
            String lon = null;
            String addres = null;

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

            int addressIndex = responseBody.indexOf("\"display_name\":\"");
            if (addressIndex != -1) {
                int addressStart = addressIndex + 16;
                int addressEnd = responseBody.indexOf("\"", addressStart);
                addres = responseBody.substring(addressStart, addressEnd);
            }

            System.out.println(lat + ", " + lon);
            System.out.println(addres);

        } else {
            System.out.println("Failed: " + response.getStatus() + " " + response.getBody());
        }
    }
}
