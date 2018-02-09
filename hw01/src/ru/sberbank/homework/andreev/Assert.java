package ru.sberbank.homework.andreev;

public class Assert {
    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertExceptions(message);
        }
        System.out.println("Test passed");
    }

    public static void assertEquals(int expected, int actual) {
        StringBuilder sb = new StringBuilder();
        sb.append("The number must be ").append(expected).append("but was ").append(actual);
        assertEquals(expected, actual, sb.toString());
    }

    public static void assertEquals(double expected, double actual, String message, double delta) {
        if (Math.abs(expected - actual) > delta) {
            throw new AssertExceptions(message);
        }
        System.out.println("Test passed");
    }

    public static void assertEquals(double expected, double actual) {
        StringBuilder sb = new StringBuilder();
        sb.append("The number must be ").append(expected).append("but was ").append(actual);
        assertEquals(expected, actual, sb.toString(), 0.000001);
    }

    public static void assertException(Runnable method, Exception expected, String message) {
        boolean passed = false;
        try {
            method.run();
        } catch (Exception e) {
            if ((e.getClass().getCanonicalName().equals(expected.getClass().getCanonicalName()))
                    && (e.getMessage().equals(expected.getMessage())))
                passed = true;
        }
        if (passed) {
            System.out.println("Test passed");
        } else {
            throw new AssertExceptions(message);
        }
    }

    public static void assertException(Runnable method, Exception expected) {
        assertException(method, expected, "Exception not equal or not throws");
    }

}

class AssertExceptions extends RuntimeException {

    public AssertExceptions() {
    }

    public AssertExceptions(String message) {
        super(message);
    }

}
