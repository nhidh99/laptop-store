package org.example.constant;

public class RegexConstants {
    public static final String REGISTRATION_USERNAME = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public static final String REGISTRATION_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$";
    public static final String REGISTRATION_PHONE = "^\\d{10}$";
    public static final String REGISTRATION_EMAIL = "^\\S+@\\S+$";
    public static final String REGISTRATION_NAME = "^.{2,30}$";
    public static final String REGISTRATION_GENDER = "MALE|FEMALE";
}
