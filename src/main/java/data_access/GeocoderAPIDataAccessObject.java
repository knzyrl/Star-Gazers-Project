package data_access;

import java.util.List;

public abstract class GeocoderAPIDataAccessObject {
    protected static final String APIkey = "6733f872eb948162981545fld4fe0b0";

    public abstract List<String> converter(String address);
}
