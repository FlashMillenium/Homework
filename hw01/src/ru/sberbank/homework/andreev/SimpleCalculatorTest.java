package ru.sberbank.homework.andreev;


import static ru.sberbank.homework.andreev.Assert.*;

public class SimpleCalculatorTest {
    public static void main(String[] args) {
        startAllTests();
    }


    public static void startAllTests(){
        happyPathIntSum();
        happyPathDoubleSum();
        happyPathIntMult();
        happyPathDoubleMult();
        happyPathIntSubstraction();
        happyPathDoubleSubstraction();
        happyPathIntDivide();
        happyPathDoubleDivide();
        exceptionIntDivideByZero();
        exceptionDoubleDivideByZero();
        exceptionIntOverflow();
        exceptionDoubleOverflow();
    }

    private static void happyPathIntSum(){
       int actual, expected = 18;
       actual = SimpleCalculator.sum(8, 10);
       assertEquals(expected, actual);
    }

    private static void happyPathDoubleSum(){
        double actual, expected = 42.42;
        actual = SimpleCalculator.sum(42, 0.42);
        assertEquals(expected,actual);
    }

    private static void happyPathIntMult(){
        int actual, expected = 144;
        actual = SimpleCalculator.multiply(12,12);
        assertEquals(expected,actual);
    }

    private static void happyPathDoubleMult(){
        double actual, expected = 5;
        actual = SimpleCalculator.multiply(10, 0.5);
        assertEquals(expected, actual);
    }

    private static void happyPathIntSubstraction(){
        int actual, expected = 75;
        actual = SimpleCalculator.substract(100, 25);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleSubstraction(){
        double actual, expected = 150.123;
        actual = SimpleCalculator.substract(300, 149.877);
        assertEquals(expected, actual);
    }

    private static void happyPathIntDivide(){
        int actual, expected = 111;
        actual = SimpleCalculator.divide(555, 5);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleDivide(){
        double actual, expected = 0.03;
        actual = SimpleCalculator.divide(0.3, 10);
        assertEquals(expected,actual);
    }
    private static void exceptionIntDivideByZero(){
        assertException(() -> SimpleCalculator.divide(10,0), new ArithmeticException());
    }

    private static void exceptionDoubleDivideByZero(){
        assertException(() -> SimpleCalculator.divide(0.314,0), new ArithmeticException());
    }

    private static void exceptionIntOverflow(){
        assertException(()-> SimpleCalculator.multiply(2_000_000, 10000), new ArithmeticException());
    }

    private static void exceptionDoubleOverflow(){
        assertException(() -> SimpleCalculator.multiply(Double.MAX_VALUE, 9999999999.1111), new ArithmeticException());
    }
}
