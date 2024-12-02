package data_access;

import java.util.ArrayList;
import java.util.List;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

/**
 * Concrete implementation of the {@link GeocoderApiDataAccessObject} for accessing geocoding data.
 * This class interacts with the Geocode API to convert an address into its geographical
 * coordinates (latitude and longitude) and retrieve the display name (full address).
 */
public class GeocoderDataAccessObject extends GeocoderApiDataAccessObject {

    /**
     * Converts a given address into geographical coordinates
     * (latitude and longitude) and a display name (full address).
     *
     * @param address The address to be converted into geographical data.
     * @return A {@link List} of {@link String} containing the longitude, latitude, and display name.
     *         Returns {@code null} if the API request fails or the address is invalid.
     */

    public List<String> converter(String address) {
        final List<String> longlat = new ArrayList();

        final String encodedAddress = "1600+Amphitheatre+Parkway+Mountain+View";
        final String newAddress = address.replace("\\s", "+");

        final String url = "https://geocode.maps.co/search?q=" + newAddress + "&api_key=" + API_KEY;

        final HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == 200) {
            final String responseBody = response.getBody();

            String lat = null;
            String lon = null;
            String properAddress = null;

            // Find the position of "lat" and extract its value
            final int latIndex = responseBody.indexOf("\"lat\":\"");
            if (latIndex != -1) {
                final int latStart = latIndex + 7;
                final int latEnd = responseBody.indexOf("\"", latStart);
                lat = responseBody.substring(latStart, latEnd);
            }

            // Find the position of "lon" and extract its value
            final int lonIndex = responseBody.indexOf("\"lon\":\"");
            if (lonIndex != -1) {
                final int lonStart = lonIndex + 7;
                final int lonEnd = responseBody.indexOf("\"", lonStart);
                lon = responseBody.substring(lonStart, lonEnd);
            }

            // Find the full address of the location given
            final int addressIndex = responseBody.indexOf("\"display_name\":\"");
            if (addressIndex != -1) {
                final int addressStart = addressIndex + 16;
                final int addressEnd = responseBody.indexOf("\"", addressStart);
                properAddress = responseBody.substring(addressStart, addressEnd);
            }

            longlat.add(lon);
            longlat.add(lat);
            longlat.add(properAddress);

            return longlat;

        }
        else {
            return null;
        }

    }

    /**
     * Main method for testing the functionality of the GeocoderDataAccessObject.
     * Sends a sample query to the Geocode API to fetch the latitude, longitude,
     * and display name for a specified address.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        final String encodedAddress = "CN+Tower";

        final String url = "https://geocode.maps.co/search?q=" + encodedAddress + "&api_key=" + API_KEY;

        final HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == 200) {
            final String responseBody = response.getBody();

            System.out.println(responseBody);

            String lat = null;
            String lon = null;
            String addres = null;

            // Find the position of "lat" and extract its value
            final int latIndex = responseBody.indexOf("\"lat\":\"");
            if (latIndex != -1) {
                final int latStart = latIndex + 7;
                final int latEnd = responseBody.indexOf("\"", latStart);
                lat = responseBody.substring(latStart, latEnd);
            }

            // Find the position of "lon" and extract its value
            final int lonIndex = responseBody.indexOf("\"lon\":\"");
            if (lonIndex != -1) {
                final int lonStart = lonIndex + 7;
                final int lonEnd = responseBody.indexOf("\"", lonStart);
                lon = responseBody.substring(lonStart, lonEnd);
            }

            final int addressIndex = responseBody.indexOf("\"display_name\":\"");
            if (addressIndex != -1) {
                final int addressStart = addressIndex + 16;
                final int addressEnd = responseBody.indexOf("\"", addressStart);
                addres = responseBody.substring(addressStart, addressEnd);
            }

            System.out.println(lat + ", " + lon);
            System.out.println(addres);

        }
        else {
            System.out.println("Failed: " + response.getStatus() + " " + response.getBody());
        }
    }
}
