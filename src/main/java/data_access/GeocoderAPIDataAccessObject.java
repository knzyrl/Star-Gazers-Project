package data_access;

import java.util.List;

public interface GeocoderAPIDataAccessObject {

    List<String> converter(String address);
}
