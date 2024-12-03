package use_case;

import data_access.NasaNeoDataAccessObject;
import entity.NearEarthObjectEntity;
import helper.NearEarthObjectsJsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.near_earth_objects.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NearEarthObjectsInteractorTest {

    private NearEarthObjectsInteractor interactor;
    private NearEarthObjectsOutputBoundary mockOutputBoundary;
    private NasaNeoDataAccessObject mockDataAccessObject;

    @BeforeEach
    public void setUp() {
        mockOutputBoundary = Mockito.mock(NearEarthObjectsOutputBoundary.class);
        mockDataAccessObject = Mockito.mock(NasaNeoDataAccessObject.class);

        interactor = new NearEarthObjectsInteractor(mockDataAccessObject, mockOutputBoundary);
    }

    @Test
    public void testFetchNEO_Success() {
        String validJsonResponse = """
                {
                    "near_earth_objects": {
                        "2024-12-01": [
                            {
                                "name": "Asteroid A",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-12-01",
                                        "miss_distance": { "kilometers": "120000.5" },
                                        "relative_velocity": { "kilometers_per_hour": "45000.0" }
                                    }
                                ]
                            }
                        ]
                    }
                }
                """;

        when(mockDataAccessObject.fetchNearEarthObjects("2024-12-01", "2024-12-07"))
                .thenReturn(validJsonResponse);

        NearEarthObjectsInputData inputData = new NearEarthObjectsInputData("2024-12-01", "2024-12-07");
        interactor.fetchNearEarthObjectsData(inputData);

        verify(mockOutputBoundary, times(1)).presentNearEarthObjectsData(any(List.class));
    }

    @Test
    public void testFetchNEO_InvalidJSON() {
        String invalidJsonResponse = "{ \"near_earth_objects\": ";

        when(mockDataAccessObject.fetchNearEarthObjects("2024-12-01", "2024-12-07"))
                .thenReturn(invalidJsonResponse);

        NearEarthObjectsInputData inputData = new NearEarthObjectsInputData("2024-12-01", "2024-12-07");
        interactor.fetchNearEarthObjectsData(inputData);

        verify(mockOutputBoundary, never()).presentNearEarthObjectsData(any());
    }

    @Test
    public void testFetchNEO_MissingFields() {
        String jsonResponse = """
            {
                "near_earth_objects": {
                    "2024-11-01": [
                        {
                            "name": "Asteroid A"
                        }
                    ]
                }
            }
            """;

        when(mockDataAccessObject.fetchNearEarthObjects("2024-11-01", "2024-11-07"))
                .thenReturn(jsonResponse);

        interactor.fetchNearEarthObjectsData(new NearEarthObjectsInputData("2024-11-01", "2024-11-07"));

        verify(mockOutputBoundary, times(1)).presentNearEarthObjectsData(argThat(list ->
                list.size() == 1 &&
                        "Asteroid A".equals(list.get(0).getName()) &&
                        "Unknown".equals(list.get(0).getClosestApproachDate()) &&
                        list.get(0).getClosestDistanceKm() == -1 &&
                        list.get(0).getRelativeVelocity() == -1
        ));
    }


    @Test
    public void testFetchNEO_NoDataFound() {
        String emptyJsonResponse = """
            {
                "near_earth_objects": {}
            }
            """;

        when(mockDataAccessObject.fetchNearEarthObjects("2024-12-01", "2024-12-07"))
                .thenReturn(emptyJsonResponse);

        NearEarthObjectsInputData inputData = new NearEarthObjectsInputData("2024-12-01", "2024-12-07");
        interactor.fetchNearEarthObjectsData(inputData);

        verify(mockOutputBoundary, times(1)).noDataFound();
        verify(mockOutputBoundary, never()).presentNearEarthObjectsData(any());
    }

    @Test
    public void testFetchNEO_EmptyInputDates() {
        NearEarthObjectsInputData inputData = new NearEarthObjectsInputData("", "");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            interactor.fetchNearEarthObjectsData(inputData);
        });

        assertEquals("Start and end dates must not be null or empty.", exception.getMessage());
    }

    @Test
    public void testParseResponse_ValidData() {
        String validJsonResponse = """
                {
                    "near_earth_objects": {
                        "2024-12-01": [
                            {
                                "name": "Asteroid A",
                                "close_approach_data": [
                                    {
                                        "close_approach_date": "2024-12-01",
                                        "miss_distance": { "kilometers": "120000.5" },
                                        "relative_velocity": { "kilometers_per_hour": "45000.0" }
                                    }
                                ]
                            }
                        ]
                    }
                }
                """;

        List<NearEarthObjectEntity> result = NearEarthObjectsJsonParser.parse(validJsonResponse);
        assertEquals(1, result.size());
        assertEquals("Asteroid A", result.get(0).getName());
        assertEquals(120000.5, result.get(0).getClosestDistanceKm());
        assertEquals(45000.0, result.get(0).getRelativeVelocity());
    }

    @Test
    public void testParseResponse_InvalidJSON() {
        String invalidJson = "{ \"near_earth_objects\": "; // Malformed JSON

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            NearEarthObjectsJsonParser.parse(invalidJson);
        });

        assertTrue(exception.getMessage().startsWith("Error parsing JSON data for Near Earth Objects"),
                "Exception message should indicate an error while parsing JSON data.");
    }

    @Test
    public void testParseResponse_EmptyJSON() {
        String emptyJson = "{}";

        List<NearEarthObjectEntity> result = NearEarthObjectsJsonParser.parse(emptyJson);

        assertTrue(result.isEmpty(), "The result should be an empty list when 'near_earth_objects' is missing.");
    }

    @Test
    public void testFetchNEO_NullResponse() {
        when(mockDataAccessObject.fetchNearEarthObjects("2024-12-01", "2024-12-07"))
                .thenReturn(null);

        NearEarthObjectsInputData inputData = new NearEarthObjectsInputData("2024-12-01", "2024-12-07");
        interactor.fetchNearEarthObjectsData(inputData);

        verify(mockOutputBoundary, times(1)).noDataFound();
        verify(mockOutputBoundary, never()).presentNearEarthObjectsData(any());
    }
}
