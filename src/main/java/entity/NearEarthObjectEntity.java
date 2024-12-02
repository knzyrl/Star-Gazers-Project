package entity;

/**
 * Represents a Near-Earth Object (NEO) with details about its name,
 * the date of its closest approach to Earth, and the closest distance in kilometers.
 */
public class NearEarthObjectEntity {
    private final String name;
    private final String closestApproachDate;
    private final double closestDistanceKm;

    public NearEarthObjectEntity(String name, String closestApproachDate, double closestDistanceKm) {
        this.name = name;
        this.closestApproachDate = closestApproachDate;
        this.closestDistanceKm = closestDistanceKm;
    }

    public String getName() {
        return name;
    }

    public String getClosestApproachDate() {
        return closestApproachDate;
    }

    public double getClosestDistanceKm() {
        return closestDistanceKm;
    }
}

