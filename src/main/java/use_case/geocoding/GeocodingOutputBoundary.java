package use_case.geocoding;

import entity.Location;

public interface GeocodingOutputBoundary {
    void execute(Location location);

    void executeBack();

    void noAddressFound();
}
