package use_case;

import data_access.AstronomyPictureApiDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.apod_date.AstronomyPictureInteractor;
import use_case.apod_date.AstronomyPictureOutputBoundary;
import use_case.apod_date.AstronomyPictureOutputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AstronomyPictureInteractorTest {

    private AstronomyPictureInteractor interactor;
    private AstronomyPictureOutputBoundary mockOutputBoundary;
    private AstronomyPictureApiDataAccessObject mockDataAccessObject;

    @BeforeEach
    public void setUp() {
        mockOutputBoundary = Mockito.mock(AstronomyPictureOutputBoundary.class);
        mockDataAccessObject = Mockito.mock(AstronomyPictureApiDataAccessObject.class);

        interactor = new AstronomyPictureInteractor(mockOutputBoundary, mockDataAccessObject, null);
    }

    @Test
    public void testFetchAstronomyPicture_Success() {
        // Mock API response
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "explanation": "This is a test AstronomyPicture description.",
                    "url": "http://example.com/image.jpg",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        // Invoke the method
        interactor.fetchAstronomyPicture();

        // Verify that the output boundary was called with the correct data
        verify(mockOutputBoundary, times(1)).presentAstronomyPicture(any(AstronomyPictureOutputData.class));
    }


    public void testFetchAstronomyPicture_InvalidJSON() {
        // Mock API response with invalid JSON (missing a colon)
        String invalidJsonResponse = "{ \"title\" \"Sample AstronomyPicture\" }"; // Invalid JSON

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(invalidJsonResponse);

        // Invoke the method and expect no crash (log or handle the error gracefully)
        interactor.fetchAstronomyPicture();

        // Ensure no output boundary calls for invalid data
        verify(mockOutputBoundary, never()).presentAstronomyPicture(any(AstronomyPictureOutputData.class));
    }

    @Test
    public void testFetchAstronomyPicture_MissingFields() {
        // Mock API response with missing fields
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        // Invoke the method
        interactor.fetchAstronomyPicture();

        // Verify that the output boundary was called with partial data
        verify(mockOutputBoundary, times(1)).presentAstronomyPicture(any(AstronomyPictureOutputData.class));
    }
}
