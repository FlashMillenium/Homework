package ru.sberbank.homework.andreev;

import static ru.sberbank.homework.andreev.Assert.*;

public class SimpleCalculatorTest {
    public static void main(String[] args) {
        Exception e = new ArithmeticException();
        Class<? extends Exception> aClass = e.getClass();
        Class arithmeticExceptionClass = ArithmeticException.class;
        System.out.println(arithmeticExceptionClass.getCanonicalName());


        startAllTests();
    }


    public static void startAllTests() {
        happyPathIntSum();
        happyPathDoubleSum();
        happyPathIntMult();
        happyPathDoubleMult();
        happyPathIntSubstraction();
        happyPathDoubleSubstraction();
        happyPathIntDivide();
        happyPathDoubleDivide();
        exceptionIntOverflow();
        exceptionDoubleOverflow();
    }

    private static void happyPathIntSum() {
        int actual, expected = 18;
        actual = SimpleCalculator.sum(8, 10);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleSum() {
        double actual, expected = 42.42;
        actual = SimpleCalculator.sum(42, 0.42);
        assertEquals(expected, actual);
    }

    private static void happyPathIntMult() {
        int actual, expected = 144;
        actual = SimpleCalculator.multiply(12, 12);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleMult() {
        double actual, expected = 99.8;
        actual = SimpleCalculator.multiply(998.0, 0.1);
        assertEquals(expected, actual);
    }

    private static void happyPathIntSubstraction() {
        int actual, expected = 75;
        actual = SimpleCalculator.substract(100, 25);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleSubstraction() {
        double actual, expected = 150.123;
        actual = SimpleCalculator.substract(300, 149.877);
        assertEquals(expected, actual);
    }

    private static void happyPathIntDivide() {
        int actual, expected = 111;
        actual = SimpleCalculator.divide(555, 5);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleDivide() {
        double actual, expected = 0.03;
        actual = SimpleCalculator.divide(0.3, 10);
        assertEquals(expected, actual);
    }

    private static void exceptionIntOverflow() {
        assertException(() -> SimpleCalculator.multiply(2_000_000, 10000),new ArithmeticException("integer overflow"));
    }

    private static void exceptionDoubleOverflow() {
        assertException(() -> SimpleCalculator.multiply(Double.MAX_VALUE, 9999999999.1111), new ArithmeticException("double overflow"));
    }
}
