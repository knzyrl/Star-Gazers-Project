package use_case.geocoding;

import entity.Location;

/**
 * Output boundry for the geocoding use case.
 */
public interface GeocodingOutputBoundary {
    /**
     * Displays the longtitude and latitude of the address.
     * @param location output data.
     */
    void execute(Location location);

    /**
     * Takes user back to homepage.
     */
    void executeBack();

    /**
     * Takes user to the no address found page.
     */
    void noAddressFound();

    /**
     * Takes user to the name view.
     */
    void executeName();
}
