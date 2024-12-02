package data_access;

import java.util.List;

/**
 * Abstract class for accessing geocoding data from an API.
 * Provides a base structure for implementing geocoding functionality, such as converting
 * an address into geographical coordinates or other location-based data.
 */
public abstract class GeocoderApiDataAccessObject {
    protected static final String APIkey = "6733f872eb948162981545fld4fe0b0";

    /**
     * Converts a given address into geographical data, such as coordinates.
     * This method is abstract and must be implemented by a subclass to perform the actual
     * geocoding operation using a specific API.
     *
     * @param address The address to be converted into geographical data.
     * @return A {@link List} of {@link String} containing the resulting geographical data.
     *         The structure of the list depends on the implementation (e.g., latitude and longitude).
     */
    public abstract List<String> converter(String address);
}
