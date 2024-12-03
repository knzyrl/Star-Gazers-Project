package helper;

import java.util.regex.Pattern;

public class NumberFormatChecker {
    /**
     * Checks whether the given string dits the pattern for a double or not.
     * @param string the string to be checked
     * @return true if the string is a double, false if not.
     */
    public static boolean checkDouble(String string) {
        // source: https://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#valueOf%28java.lang.String%29
        final String digits = "(\\p{Digit}+)";
        final String hexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String exp = "[eE][+-]?" + digits;
        final String fpRegex =
                "[\\x00-\\x20]*" + "[+-]?(" + "NaN|" + "Infinity|"
                        + "(((" + digits + "(\\.)?(" + digits + "?)(" + exp + ")?)|"
                        + "(\\.(" + digits + ")(" + exp + ")?)|" + "((" + "(0[xX]" + hexDigits + "(\\.)?)|"
                        + "(0[xX]" + hexDigits + "?(\\.)" + hexDigits + ")" + ")[pP][+-]?" + digits + "))"
                        + "[fFdD]?))" + "[\\x00-\\x20]*";
        return Pattern.matches(fpRegex, string);
    }
}
