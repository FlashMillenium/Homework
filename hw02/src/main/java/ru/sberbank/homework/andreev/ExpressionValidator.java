package ru.sberbank.homework.andreev;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpressionValidator {
    private String expression;
    private Optional<String> wrongElement;

    ExpressionValidator(String expression) {
        this.expression = expression;
        wrongElement = Optional.empty();
    }

    public boolean isExpressionValid() {
        String regexp="(.+ )?(" + RegExp.OPERATIONS + " .+)+";
        return expression.matches(regexp);
    }

    public boolean haveWrongElement() {
        wrongElement = Arrays.stream(expression.split(" "))
                .filter((s) -> !(s.matches(RegExp.LITERAL) || s.matches(RegExp.OPERATIONS))).findFirst();
        return wrongElement.isPresent();
    }

    public String getWrongElement() {
        return wrongElement.orElse("Checking don't happened or all element is valid");
    }

    private static class RegExp {
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
