package use_case;

import data_access.NasaNeoDataAccessObject;
import entity.NearEarthObjectEntity;
import interface_adapter.near_earth_objects.NearEarthObjectsPresenter;
import use_case.near_earth_objects.NearEarthObjectsInputData;
import use_case.near_earth_objects.NearEarthObjectsInteractor;
import view.DisplayNearEarthObjectsView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NearEarthObjectsInteractorTest {
    public static void main(String[] args) throws IOException {

        // Mock DAO simulating a realistic JSON response from the NASA API
        NasaNeoDataAccessObject mockDAO = new NasaNeoDataAccessObject() {
            @Override
            public String fetchNearEarthObjects(String startDate, String endDate) {
                return """
                {
                    "near_earth_objects": {
                        "2024-11-01": [
                            {
                                "name": "Asteroid A",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-11-01",
                                        "miss_distance": { "kilometers": "120000.5" },
                                        "relative_velocity": { "kilometers_per_hour": "45000.0" }
                                    }
                                ]
                            },
                            {
                                "name": "Asteroid B",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-11-01",
                                        "miss_distance": { "kilometers": "340000.2" },
                                        "relative_velocity": { "kilometers_per_hour": "36000.0" }
                                    }
                                ]
                            }
                        ]
                    }
                }
                """;
            }
        };

        // Create a CardLayout and JPanel for the ViewManager
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Instantiate the ViewManager with the CardLayout and JPanel
        ViewManager viewManager = new ViewManager(cardLayout, cardPanel);

        // Mock DisplayNearEarthObjectsView to simulate rendering data
        DisplayNearEarthObjectsView mockDisplayNearEarthObjectsView = new DisplayNearEarthObjectsView() {
            @Override
            public void displayNearEarthObjectsData(java.util.List<NearEarthObjectEntity> neoEntities) {
                System.out.println("Displaying Near-Earth Objects:");
                for (var neo : neoEntities) {
                    System.out.printf("Name: %s, Closest Approach Date: %s, Distance: %.2f km%n, Velocity: %.2f km/h%n",
                            neo.getName(), neo.getClosestApproachDate(), neo.getClosestDistanceKm(), neo.getRelativeVelocity());
                }
            }
        };

        // Create Presenter and wire it with the ViewManager and DisplayNearEarthObjectsView
        NearEarthObjectsPresenter presenter = new NearEarthObjectsPresenter(viewManager);
        presenter.setDisplayNeoView(mockDisplayNearEarthObjectsView);

        // Create Interactor and wire it with the mock DAO and Presenter
        NearEarthObjectsInteractor interactor = new NearEarthObjectsInteractor(mockDAO, presenter);

        // Execute the use case
        interactor.fetchNearEarthObjectsData(new NearEarthObjectsInputData("2024-11-01", "2024-11-07"));
    }
}
