package use_case;

import data_access.AstronomyPictureApiDataAccessObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.apod_date.APODInteractor;
import use_case.apod_date.APODOutputBoundary;
import use_case.apod_date.APODOutputData;
import view.ViewManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AstronomyPictureInteractorTest {

    private APODInteractor interactor;
    private APODOutputBoundary mockOutputBoundary;
    private AstronomyPictureApiDataAccessObject mockDataAccessObject;
    private ViewManager mockViewManager;

    @BeforeEach
    public void setUp() {
        mockOutputBoundary = Mockito.mock(APODOutputBoundary.class);
        mockDataAccessObject = Mockito.mock(AstronomyPictureApiDataAccessObject.class);
        mockViewManager = Mockito.mock(ViewManager.class);

        interactor = new APODInteractor(mockOutputBoundary, mockDataAccessObject, mockViewManager);
    }

    @Test
    public void testFetchAPOD_Success() {
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "explanation": "This is a test AstronomyPicture description.",
                    "url": "http://example.com/image.jpg",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        interactor.fetchAPOD();

        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testFetchAPOD_InvalidJSON() {
        String invalidJsonResponse = "{ \"title\" \"Sample AstronomyPicture\" }"; // Malformed JSON

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(invalidJsonResponse);

        // Invoke the method
        interactor.fetchAPOD();

        // Ensure no output boundary calls were made
        verify(mockOutputBoundary, never()).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testFetchAPOD_MissingFields() {
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        interactor.fetchAPOD();

        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testFetchAPODByDate_Success() {
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "explanation": "This is a test AstronomyPicture description.",
                    "url": "http://example.com/image.jpg",
                    "media_type": "image"
                }
                """;

        String date = "2023-12-01";
        when(mockDataAccessObject.fetchAstronomyPictureByDate(date)).thenReturn(jsonResponse);

        interactor.fetchAPODByDate(date);

        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testFetchAPODByDate_InvalidJSON() {
        String invalidJsonResponse = "{ \"title\" \"Sample AstronomyPicture\" }"; // Malformed JSON

        when(mockDataAccessObject.fetchAstronomyPictureByDate("2023-12-01")).thenReturn(invalidJsonResponse);

        // Invoke the method
        interactor.fetchAPODByDate("2023-12-01");

        // Ensure no output boundary calls were made
        verify(mockOutputBoundary, never()).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testGoBackToHome() {
        interactor.goBackToHome();

        verify(mockViewManager, times(1)).show("home");
    }

    @Test
    public void testProcessResponse_EmptyJSON() {
        String emptyJsonResponse = "{}";

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(emptyJsonResponse);

        interactor.fetchAPOD();

        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }

    @Test
    public void testProcessResponse_InvalidDataType() {
        String invalidDataTypeJson = """
                {
                    "title": 123,
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(invalidDataTypeJson);

        interactor.fetchAPOD();

        verify(mockOutputBoundary, times(1)).presentAPOD(any(APODOutputData.class));
    }
}
