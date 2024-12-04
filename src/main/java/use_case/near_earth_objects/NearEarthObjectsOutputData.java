package use_case.near_earth_objects;

/**
 * Class for output data for Near earth Objects.
 */
public class NearEarthObjectsOutputData {
    private String name;
    private String closestApproachDate;
    private double closestDistance;
    private double relativeVelocity;

    public NearEarthObjectsOutputData(String name, String closestApproachDate, double closestDistance,
                                      double relativeVelocity) {
        this.name = name;
        this.closestApproachDate = closestApproachDate;
        this.closestDistance = closestDistance;
        this.relativeVelocity = relativeVelocity;
    }

    public String getName() {
        return this.name;
    }

    public String getClosestApproachDate() {
        return this.closestApproachDate;
    }

    public double getClosestDistanceKm() {
        return this.closestDistance;
    }

    public double getRelativeVelocity() {
        return this.relativeVelocity;
    }
}
