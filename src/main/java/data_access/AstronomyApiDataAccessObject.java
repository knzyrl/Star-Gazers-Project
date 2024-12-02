package data_access;

import java.util.Base64;

/**
 * Abstract class for accessing astronomy-related data through an API.
 * Provides authentication details and an abstract method for executing queries.
 */
public abstract class AstronomyApiDataAccessObject {
    protected static final String APP_ID = "44f82562-233e-4f52-af5d-ca47ea0decc6";
    protected static final String APP_SECRET = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5"
            + "ff34eec5bcf8a9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c"
            + "25015d0a67653f07de92d3577c82a6a44af9e051eb2aa8c30f0fac00887d322d"
            + "3992a1125a6b630bc94e3e122cf28d6b";
    protected static final String AUTH_STRING = Base64.getEncoder()
            .encodeToString((APP_ID + ":" + APP_SECRET).getBytes());

    /**
     * Executes a query to retrieve data from the astronomy API.
     *
     * @param query The query string to execute.
     * @param <T> The type of the data expected as a response.
     * @return The response data of type {@code T}.
     */
    public abstract <T> T executeQuery(String query);
}
