package exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RentalExceptionTest {

    @Test
    void testDefaultConstructor() {
        RentalException exception = assertThrows(RentalException.class, () -> {
            throw new RentalException();
        });

        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithExceptionCode() {
        RentalException exception = assertThrows(RentalException.class, () -> {
            throw new RentalException(ExceptionCode.ERR01);
        });

        assertEquals("ERR01:The configuration file is not found! File name is ", exception.getMessage());
    }

    @Test
    void testConstructorWithExceptionCodeAndMessage() {
        RentalException exception = assertThrows(RentalException.class, () -> {
            throw new RentalException(ExceptionCode.ERR02, "Custom message");
        });

        assertEquals("ERR02:Error loading configuration from the file name Custom message", exception.getMessage());
    }

    @Test
    void testConstructorWithExceptionCodeAndMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");

        RentalException exception = assertThrows(RentalException.class, () -> {
            throw new RentalException(ExceptionCode.ERR03, "Custom message", cause);
        });

        assertEquals("ERR03:The configuration property is not setup correctly. Please run the program again!Custom message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Root cause");

        RentalException exception = assertThrows(RentalException.class, () -> {
            throw new RentalException(cause);
        });

        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
