package use_case;
import use_case.apod_date.ApodInputData;

import data_access.AstronomyPictureApiDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.Mockito;
import use_case.apod_date.ApodInteractor;
import use_case.apod_date.ApodOutputBoundary;
import use_case.apod_date.ApodOutputData;
import view.ViewManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AstronomyPictureInteractorTest {

    private ApodInteractor interactor;
    private ApodOutputBoundary mockOutputBoundary;
    private AstronomyPictureApiDataAccessObject mockDataAccessObject;
    private ViewManager mockViewManager;

    @BeforeEach
    public void setUp() {
        mockOutputBoundary = Mockito.mock(ApodOutputBoundary.class);
        mockDataAccessObject = Mockito.mock(AstronomyPictureApiDataAccessObject.class);
        mockViewManager = Mockito.mock(ViewManager.class);

        interactor = new ApodInteractor(mockOutputBoundary, mockDataAccessObject, mockViewManager);
    }

    @Test
    public void testFetchApod_Success() {
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "explanation": "This is a test AstronomyPicture description.",
                    "url": "http://example.com/image.jpg",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        interactor.fetchApod();

        verify(mockOutputBoundary, times(1)).presentApod(any(ApodOutputData.class));
    }

    @Test
    public void testFetchApod_InvalidJSON() {
        String invalidJsonResponse = "{ \"title\" \"Sample AstronomyPicture\" }"; // Malformed JSON

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(invalidJsonResponse);

        // Invoke the method
        interactor.fetchApod();

        // Ensure no output boundary calls were made
        verify(mockOutputBoundary, never()).presentApod(any(ApodOutputData.class));
    }

    @Test
    public void testFetchApod_MissingFields() {
        String jsonResponse = """
                {
                    "title": "Sample AstronomyPicture",
                    "media_type": "image"
                }
                """;

        when(mockDataAccessObject.fetchAstronomyPicture()).thenReturn(jsonResponse);

        interactor.fetchApod();

        verify(mockOutputBoundary, times(1)).presentApod(any(ApodOutputData.class));
    }

    @Test
    public void testFetchApodByDate_Success() {
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

        interactor.fetchApodByDate(date);

        verify(mockOutputBoundary, times(1)).presentApod(any(ApodOutputData.class));
    }

    @Test
    public void testFetchApodByDate_InvalidJSON() {
        String invalidJsonResponse = "{ \"title\" \"Sample AstronomyPicture\" }"; // Malformed JSON

        when(mockDataAccessObject.fetchAstronomyPictureByDate("2023-12-01")).thenReturn(invalidJsonResponse);

        // Invoke the method
        interactor.fetchApodByDate("2023-12-01");

        // Ensure no output boundary calls were made
        verify(mockOutputBoundary, never()).presentApod(any(ApodOutputData.class));
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

        interactor.fetchApod();

        verify(mockOutputBoundary, times(1)).presentApod(any(ApodOutputData.class));
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

        interactor.fetchApod();

        verify(mockOutputBoundary, times(1)).presentApod(any(ApodOutputData.class));
    }

    @Test
    public void testApodOutputData_AllFields() {
        String title = "A Beautiful Galaxy";
        String description = "A description of a beautiful galaxy.";
        String mediaType = "image";
        String url = "http://example.com/image.jpg";
        String thumbnailUrl = "http://example.com/thumb.jpg";

        // Create an APODOutputData object
        ApodOutputData outputData = new ApodOutputData(title, description, mediaType, url, thumbnailUrl);

        // Assert all fields
        assertEquals(title, outputData.getTitle());
        assertEquals(description, outputData.getDescription());
        assertEquals(mediaType, outputData.getMediaType());
        assertEquals(url, outputData.getUrl());
        assertEquals(thumbnailUrl, outputData.getThumbnailUrl());
    }

    @Test
    public void testApodOutputData_DefaultThumbnailUrl() {
        String title = "A Beautiful Galaxy";
        String description = "A description of a beautiful galaxy.";
        String mediaType = "image";
        String url = "http://example.com/image.jpg";
        String thumbnailUrl = "";

        // Create an APODOutputData object
        ApodOutputData outputData = new ApodOutputData(title, description, mediaType, url, thumbnailUrl);

        // Assert all fields, including default thumbnail
        assertEquals(title, outputData.getTitle());
        assertEquals(description, outputData.getDescription());
        assertEquals(mediaType, outputData.getMediaType());
        assertEquals(url, outputData.getUrl());
        assertEquals(thumbnailUrl, outputData.getThumbnailUrl());
    }

    @Test
    public void testApodOutputData_EmptyFields() {
        String title = "";
        String description = "";
        String mediaType = "";
        String url = "";
        String thumbnailUrl = "";

        // Create an APODOutputData object
        ApodOutputData outputData = new ApodOutputData(title, description, mediaType, url, thumbnailUrl);

        // Assert all fields
        assertEquals(title, outputData.getTitle());
        assertEquals(description, outputData.getDescription());
        assertEquals(mediaType, outputData.getMediaType());
        assertEquals(url, outputData.getUrl());
        assertEquals(thumbnailUrl, outputData.getThumbnailUrl());
    }

    @Test
    public void testApodInputData_Instantiation() {
        // Instantiate the APODInputData class
        ApodInputData inputData = new ApodInputData();

        // Verify that the instance is not null
        assertNotNull(inputData, "APODInputData instance should not be null");
    }
}
