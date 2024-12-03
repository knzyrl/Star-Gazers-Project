package helper;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to perform Astronomy calculations for Star Chart use case.
 */

public class AstronomyCalculations {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final LocalDate MJD_START = LocalDate.of(1858, 11, 17);
    private static final int DEGREES = 360;
    private static final int HOURS = 15;

    /**
     * Calculates the right-ascension coordinate at the zenith of a location given its longitude and the date.
     * @param longitude the longitude specified by the user
     * @param date the date specified by the user
     * @return the right-ascension coordinate as a double
     */
    public static double calcRa(String longitude, String date) {
        double ra = (calcGmst(date) + Double.parseDouble(longitude)) % DEGREES;
        if (ra < 0) {
            ra += DEGREES;
        }
        // Convert to hours
        return Math.round(ra / HOURS);
    }

    private static double calcGmst(String date) {
        final long mjd = calcMjd(date);
        // calculate T
        final double t = (mjd - 51544.5) / 36525.0;
        // do calculation
        double gmst = ((280.46061837 + 360.98564736629 * (mjd - 51544.5)) + 0.000387933 * t * t - t
                * t * t / 38710000.0) % 360.0;
        // make positive
        if (gmst < 0) {
            gmst += DEGREES;
        }
        return gmst;
    }

    private static long calcMjd(String date) {
        final LocalDate gd = LocalDate.parse(date, DTF);
        return DAYS.between(MJD_START, gd);
    }
}
