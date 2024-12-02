package helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class AstronomyCalculations {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final LocalDate MJD_START = LocalDate.of(1858, 11, 17);

    // Sourced from https://www.ssec.wisc.edu/mcidas/software/v/javadoc/1.01/src-html/edu/wisc/ssec/mcidasv/data/adde/sgp4/Sidereal.html
    public static double calcRA(String longitude, String date) {
        double ra = (calcGMST(date) + Double.parseDouble(longitude)) % 360;
        if (ra < 0) {
            ra += 360;
        }
        // Convert to hours
        return Math.round(ra / 15);
    }

    private static double calcGMST(String date) {
        long mjd = calcMJD(date);
        // calculate T
        double t = (mjd-51544.5)/36525.0;
        // do calculation
        double gmst = ( (280.46061837 + 360.98564736629*(mjd-51544.5)) + 0.000387933*t*t - t*t*t/38710000.0) % 360.0;
        // make positive
        if(gmst < 0) {
            gmst += 360.0;
        }
        return gmst;
    }

    private static long calcMJD(String date) {
        LocalDate gd = LocalDate.parse(date, DTF);
        return DAYS.between(MJD_START, gd);
    }
}
