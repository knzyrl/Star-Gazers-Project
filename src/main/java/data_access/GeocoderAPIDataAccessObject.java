package data_access;

import java.util.List;

public interface GeocoderAPIDataAccessObject {

    public abstract List<String> converter(String address);
}
