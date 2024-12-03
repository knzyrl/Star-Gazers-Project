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
public class GeocoderDataAccessObject implements GeocoderApiDataAccessObject {

    /**
     * Converts a given address into geographical coordinates
     * (latitude and longitude) and a display name (full address).
     *
     * @param address The address to be converted into geographical data.
     * @return A {@link List} of {@link String} containing the longitude, latitude, and display name.
     *         Returns {@code null} if the API request fails or the address is invalid.
     */

    public List<String> converter(String address) {
        final String apiKey = "6733f872eb948162981545fld4fe0b0";
        final List<String> longlat = new ArrayList();
        final int validResponseCode = 200;

        final String encodedAddress = "1600+Amphitheatre+Parkway+Mountain+View";
        final String newAddress = address.replace("\\s", "+");

        final String url = "https://geocode.maps.co/search?q=" + newAddress + "&api_key=" + apiKey;

        final HttpResponse<String> response = Unirest.get(url).asString();

        // Handle the response
        if (response.getStatus() == validResponseCode) {
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

}
