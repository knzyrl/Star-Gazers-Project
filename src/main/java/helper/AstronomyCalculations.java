package helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Provides astronomical calculations, such as calculating the Right Ascension (RA)
 * and the Greenwich Mean Sidereal Time (GMST), based on a given longitude and date.
 */
public class AstronomyCalculations {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final LocalDate MJD_START = LocalDate.of(1858, 11, 17);

    // Sourced from https://www.ssec.wisc.edu/mcidas/software/v/javadoc/1.01/src-html/edu/wisc/ssec/
    // mcidasv/data/adde/sgp4/Sidereal.html
    /**
     * Calculates the Right Ascension (RA) based on the given longitude and date.
     *
     * @param longitude The longitude in degrees as a {@link String}.
     * @param date      The date in the format "yyyy-MM-dd".
     * @return The Right Ascension in hours as a {@code double}.
     */
    public static double calcRa(String longitude, String date) {
        double ra = (calcGmst(date) + Double.parseDouble(longitude)) % 360;
        if (ra < 0) {
            ra += 360;
        }
        // Convert to hours
        return Math.round(ra / 15);
    }

    /**
     * Calculates the Greenwich Mean Sidereal Time (GMST) for a given date.
     *
     * @param date The date in the format "yyyy-MM-dd".
     * @return The GMST in degrees as a {@code double}.
     */
    private static double calcGmst(String date) {
        final long mjd = calcMjd(date);
        // calculate T
        final double t = (mjd - 51544.5) / 36525.0;
        // do calculation
        double gmst = ((280.46061837 + 360.98564736629 * (mjd - 51544.5)) + 0.000387933 * t * t - t * t * t
                / 38710000.0) % 360.0;
        // make positive
        if (gmst < 0) {
            gmst += 360.0;
        }
        return gmst;
    }

    private static long calcMjd(String date) {
        final LocalDate gd = LocalDate.parse(date, DTF);
        return ChronoUnit.DAYS.between(MJD_START, gd);
    }
}
