package com.panono.panorama.exception;
/**
 *
 * @author uday
 */
public class TimestampException extends IllegalArgumentException {
    public TimestampException(String message) {
        super(message);
    }

    public static void validate(boolean condition, String message, Object... args) {
        if (!condition) {
            throw new TimestampException(String.format(message, args));
        }
    }
}
