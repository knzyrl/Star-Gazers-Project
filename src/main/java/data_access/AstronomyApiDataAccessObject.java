package data_access;

/**
 * Interface for executing queries to astronomy-related APIs.
 */
public interface AstronomyApiDataAccessObject {

    /**
     * Executes a query to fetch data from an astronomy API.
     *
     * @param query The query string to execute.
     * @param <T>   The type of the data expected as a response.
     * @return The response data of type {@code T}.
     */
    <T> T executeQuery(String query);
}
