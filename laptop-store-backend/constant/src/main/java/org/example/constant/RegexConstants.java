package org.example.constant;

public class RegexConstants {
    /**
     * Regex for registered username:
     *
     * - 8 to 20 character long.
     *
     * - Allowed a-z A-Z 0-9 characters and symbols _ or .
     *
     * - No _ or . at the beginning.
     *
     * - No adjacent _ or . inside.
     *
     * - No _ or . at the end.
     */
    public static final String REGISTRATION_USERNAME = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    /**
     * Regex for registered password:
     *
     * - 8 to 30 character long.
     *
     * - At least one uppercase letter.
     *
     * - At least one lowercase letter.
     *
     * - At least one number.
     *
     * - At least one special character).
     */
    public static final String REGISTRATION_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$";

    /**
     * Regex for registered phone: only accept 10 digit number
     */
    public static final String REGISTRATION_PHONE = "^\\d{10}$";

    /**
     * Regex for registered email: only accept one '@' character
     */
    public static final String REGISTRATION_EMAIL = "^\\S+@\\S+$";

    /**
     * Regex for registered name: 2 to 30 character long
     */
    public static final String REGISTRATION_NAME = "^.{2,30}$";

    /**
     * Regex for registered gender: MALE or FEMALE
     */
    public static final String REGISTRATION_GENDER = "MALE|FEMALE";
}
