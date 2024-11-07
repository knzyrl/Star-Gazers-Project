package data_access;

import java.util.Base64;

public abstract class AstronomyAPIDataAccessObject {
    protected static final String APP_ID = "44f82562-233e-4f52-af5d-ca47ea0decc6";
    protected static final String APP_SECRET = "7e5f03d003ca9fb895eec14a61da205ae1a577578911b4abfde55226093e7dd5ff34eec5bcf8a9bdb6881a5cda86ef312cc1ef0c466f647bbc3a429db7cceb0c25015d0a67653f07de92d3577c82a6a44af9e051eb2aa8c30f0fac00887d322d3992a1125a6b630bc94e3e122cf28d6b";
    protected static final String AUTH_STRING = Base64.getEncoder().encodeToString((APP_ID + ":" + APP_SECRET).getBytes());

    public abstract String executeQuery(String query);
}
