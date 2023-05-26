package spring_basic_employee.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://localhost";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");

    private ErrorConstants() {}
}
