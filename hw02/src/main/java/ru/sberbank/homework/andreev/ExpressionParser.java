package ru.sberbank.homework.andreev;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionParser {
    private List<BigDecimal> elements;
    private List<Operation> operation;

    public List<BigDecimal> getElements() {
        return elements;
    }

    public List<Operation> getOperation() {
        return operation;
    }

    ExpressionParser(String expression) {
        String[] splitExpression = expression.split(" ");
        operation = collectOperations(splitExpression);
        elements = collectElements(splitExpression);
    }

    private List<Operation> collectOperations(String[] splitExpression) {
        return Arrays.stream(splitExpression)
                .filter(e -> e.matches(RegExp.OPERATIONS))
                .map(this::getOperationOnSymbol)
                .collect(Collectors.toList());
    }

    private Operation getOperationOnSymbol(String symbol) {
        return Arrays.stream(Operation.values())
                .filter(e -> symbol.matches(e.getRegExpSymbol())).findFirst().get();
    }

    private List<BigDecimal> collectElements(String[] splitExpression) {
        return Arrays.stream(splitExpression)
                .filter(e -> e.matches(RegExp.LITERAL))
                .map(this::convertElement)
                .collect(Collectors.toList());
    }

    private BigDecimal convertElement(String element) {
        String innerElement = prepareElementForConversion(element);
        BigDecimal result = null;
        if (element.matches(RegExp.INTDECIMAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement));
        } else if (element.matches(RegExp.INTOCTAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.substring(1), 8));
        } else if (element.matches(RegExp.INTHEXADECIMAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.substring(2), 16));
        } else if (element.matches(RegExp.INTBINARY)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.substring(2), 2));
        } else if (element.matches(RegExp.FlOAT)) {
            result = BigDecimal.valueOf(Double.parseDouble(innerElement));
        }
        return result;
    }

    private String prepareElementForConversion(String element) {
        String elementWithoutUnderscore = element.replace("_", "");
        char lastCharacter = Character.toLowerCase(elementWithoutUnderscore.charAt(elementWithoutUnderscore.length() - 1));
        if (lastCharacter == 'l') {
            return elementWithoutUnderscore.substring(0, elementWithoutUnderscore.length() - 1);
        }
        return elementWithoutUnderscore;
    }

    private static class RegExp {
        private static String INTDECIMAL = "[+\\-]?(0|([1-9]([0-9_]*\\d)?))[lL]?";
        private static String INTOCTAL = "[+\\-]?0([0-7]|_)*[0-7][lL]?";
        private static String INTHEXADECIMAL = "[+\\-]?0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[lL]?";
        private static String INTBINARY = "[+\\-]?0[bB][01]([01_]*[01])?[lL]?";
        private static String FlOAT = "[+\\-]?" + "(" +
                "((\\d([0-9_]*\\d)?)\\.(\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
                "((\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
                "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[pP][+\\-]?\\d([0-9_]*\\d)?)" + ")";
        private static String LITERAL = "[+\\-]?" + "(" +
                "((0|([1-9]([0-9_]*\\d)?))[lL]?)" + "|" +
                "(0([0-7]|_)*[0-7][lL]?)" + "|" +
                "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[lL]?)" + "|" +
                "(0[bB][01]([01_]*[01])?[lL]?)" + "|" +
                "(" + "((\\d([0-9_]*\\d)?)\\.(\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
                "((\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
                "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[pP][+\\-]?\\d([0-9_]*\\d)?)" +
                ")" + ")";
        private static String OPERATIONS = "[" + Arrays.stream(Operation.values())
                .map(Operation::getRegExpSymbol)
                .collect(Collectors.joining()) + "]";
    }
}
