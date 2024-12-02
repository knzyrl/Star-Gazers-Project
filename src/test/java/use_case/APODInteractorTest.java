package use_case;

import data_access.AstronomyPictureApiDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.apod_date.APODInteractor;
import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class APODInteractorTest {

    private APODInteractor interactor;
    private APODOutputBoundary mockOutputBoundary;
    private AstronomyPictureApiDataAccessObject mockDataAccessObject;

    @BeforeEach
    public void setUp() {
        mockOutputBoundary = Mockito.mock(APODOutputBoundary.class);
        mockDataAccessObject = Mockito.mock(AstronomyPictureApiDataAccessObject.class);

        interactor = new APODInteractor(mockOutputBoundary, mockDataAccessObject, null);
    }

    @Test
    public void testFetchAPOD_Success() {
        // Mock API response
        String jsonResponse = """
                {
                    "title": "Sample APOD",
                    "explanation": "This is a test APOD description.",
                    "url": "http://example.com/image.jpg",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        // Invoke the method
        interactor.fetchAPOD();

        // Verify that the output boundary was called with the correct data
        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }


    public void testFetchAPOD_InvalidJSON() {
        // Mock API response with invalid JSON (missing a colon)
        String invalidJsonResponse = "{ \"title\" \"Sample APOD\" }"; // Invalid JSON

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(invalidJsonResponse);

        // Invoke the method and expect no crash (log or handle the error gracefully)
        interactor.fetchAPOD();

        // Ensure no output boundary calls for invalid data
        verify(mockOutputBoundary, never()).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testFetchAPOD_MissingFields() {
        // Mock API response with missing fields
        String jsonResponse = """
                {
                    "title": "Sample APOD",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        // Invoke the method
        interactor.fetchAPOD();

        // Verify that the output boundary was called with partial data
        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }
}
