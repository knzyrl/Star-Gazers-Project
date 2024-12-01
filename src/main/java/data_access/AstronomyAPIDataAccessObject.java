package data_access;

public interface AstronomyAPIDataAccessObject {

    <T> T executeQuery(String query);
}
