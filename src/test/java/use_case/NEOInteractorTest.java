package use_case;

import data_access.NASANeoDataAccessObject;
import interface_adapter.near_earth_objects.NEOPresenter;
import use_case.near_earth_objects.NEOInputData;
import use_case.near_earth_objects.NEOInteractor;

import java.io.IOException;

public class NEOInteractorTest {
    public static void main(String[] args) throws IOException {

        NASANeoDataAccessObject mockDAO = new NASANeoDataAccessObject() {
            @Override
            public String fetchNearEarthObjects(String startDate, String endDate) {
                // Simulating a realistic JSON response from the NASA API
                return """
                {
                    "near_earth_objects": {
                        "2024-11-01": [
                            {
                                "name": "Asteroid A",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-11-01",
                                        "miss_distance": { "kilometers": "120000.5" }
                                    }
                                ]
                            },
                            {
                                "name": "Asteroid B",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-11-01",
                                        "miss_distance": { "kilometers": "340000.2" }
                                    }
                                ]
                            }
                        ]
                    }
                }
                """;
            }
        };

        NEOPresenter presenter = new NEOPresenter();

        NEOInteractor interactor = new NEOInteractor(mockDAO, presenter);

        interactor.fetchNEOData(new NEOInputData("2024-11-01", "2024-11-07"));
    }
}
