package entity;

/**
 * Represents a Near-Earth Object (NEO) with details about its name,
 * the date of its closest approach to Earth, and the closest distance in kilometers.
 */
public class NearEarthObjectEntity {
    private final String name;
    private final String closestApproachDate;
    private final double closestDistanceKm;

    /**
     * Constructs a Near-Earth Object (NEO) entity with the specified details.
     *
     * @param name                The name of the NEO.
     * @param closestApproachDate The date of the closest approach to Earth in the format YYYY-MM-DD.
     * @param closestDistanceKm   The closest distance of the NEO to Earth in kilometers.
     */

    public NearEarthObjectEntity(String name, String closestApproachDate, double closestDistanceKm) {
        this.name = name;
        this.closestApproachDate = closestApproachDate;
        this.closestDistanceKm = closestDistanceKm;
    }

    /**
     * Returns the name of the Near-Earth Object.
     *
     * @return The name of the NEO.
     */

    public String getName() {
        return name;
    }

    /**
     * Returns the date of the NEO's closest approach to Earth.
     *
     * @return The closest approach date in the format YYYY-MM-DD.
     */

    public String getClosestApproachDate() {
        return closestApproachDate;
    }

    /**
     * Returns the closest distance of the NEO to Earth in kilometers.
     *
     * @return The closest distance in kilometers.
     */

    public double getClosestDistanceKm() {
        return closestDistanceKm;
    }
}

