package data_access;

import java.util.List;

/**
 * Interface for accessing geocoding data from an API.
 * Provides a base structure for implementing geocoding functionality, such as converting
 * an address into geographical coordinates or other location-based data.
 */
public interface GeocoderApiDataAccessObject {

    /**
     * Converts a given address into geographical data, such as coordinates.
     *
     * @param address The address to be converted into geographical data.
     * @return A {@link List} of {@link String} containing the resulting geographical data.
     *         The structure of the list depends on the implementation (e.g., latitude and longitude).
     */
    List<String> converter(String address);
}
