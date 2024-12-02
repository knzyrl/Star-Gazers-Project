package helper;

import java.util.regex.Pattern;

/**
 * Utility class for checking if a given string represents a valid double-precision floating-point number.
 */

public class NumberFormatChecker {

    /**
     * Checks if the given string matches the pattern for a valid double value.
     *
     * @param input The string to be checked.
     * @return {@code true} if the string is a valid double value; {@code false} otherwise.
     */
    public static boolean checkDouble(String input) {
        // source: https://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#valueOf%28java.lang.String%29
        final String digits = "(\\p{Digit}+)";
        final String hexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String exponent = "[eE][+-]?" + digits;
        final String fpRegex =
                // Optional leading "whitespace"
                ("[\\x00-\\x20]*"
                        +
                        // Optional sign character
                        "[+-]?("
                        +
                        // "NaN" string
                        "NaN|"
                        +
                        // "Infinity" string
                        "Infinity|"
                        +

                        // A decimal floating-point string representing a finite positive
                        // number without a leading sign has at most five basic pieces:
                        // digits . digits ExponentPart FloatTypeSuffix
                        //
                        // Since this method allows integer-only strings as input
                        // in addition to strings of floating-point literals, the
                        // two sub-patterns below are simplifications of the grammar
                        // productions from the Java Language Specification, 2nd
                        // edition, section 3.10.2.

                        // digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                        "(((" + digits + "(\\.)?(" + digits + "?)(" + exponent + ")?)|"
                        +
                        // . digits ExponentPart_opt FloatTypeSuffix_opt
                        "(\\.(" + digits + ")(" + exponent + ")?)|"
                        +
                        // Hexadecimal strings
                        "(("
                        // 0[xX] hexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                        +
                        "(0[xX]" + hexDigits + "(\\.)?)|"
                        +
                        // 0[xX] HexDigits_opt . hexDigits BinaryExponent FloatTypeSuffix_opt
                        "(0[xX]" + hexDigits + "?(\\.)" + hexDigits + ")"
                        +
                        ")[pP][+-]?" + digits + "))"
                        + "[fFdD]?))"
                        // Optional trailing "whitespace"
                        + "[\\x00-\\x20]*");

        if (Pattern.matches(fpRegex, input)) {
            return true;
        }
        else {
            return false;
        }
    }
}
