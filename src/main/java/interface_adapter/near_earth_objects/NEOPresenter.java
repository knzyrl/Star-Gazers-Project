package interface_adapter.near_earth_objects;

import entity.NEOEntity;
import use_case.near_earth_objects.NEOOutputBoundary;

import java.util.List;

public class NEOPresenter implements NEOOutputBoundary {

    @Override
    public void presentNEOData(List<NEOEntity> neoData) {
        System.out.println("Nearby Asteroids:");
        for (NEOEntity asteroid : neoData) {
            System.out.printf("Name: %s, Closest Approach Date: %s, Distance: %.2f km%n",
                    asteroid.getName(),
                    asteroid.getClosestApproachDate(),
                    asteroid.getClosestDistanceKm());
        }
    }
}
