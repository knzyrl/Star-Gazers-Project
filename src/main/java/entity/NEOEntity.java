package entity;

public class NEOEntity {
    private final String name;
    private final String closestApproachDate;
    private final double closestDistanceKm;

    public NEOEntity(String name, String closestApproachDate, double closestDistanceKm) {
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

