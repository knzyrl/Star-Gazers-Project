package use_case.geocoding;

/**
 * Output boundry for the geocoding use case.
 */
public interface GeocodingOutputBoundary {
    /**
     * Displays the longtitude and latitude of the address.
     * @param geocodingOutputData output data.
     */
    void execute(GeocodingOutputData geocodingOutputData);

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
