package sample.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionParserTest {

    @Test
    public void convertFromDecToBinary() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("111", exprParser.convertExpression("7", 10, 2));
    }

    @Test
    public void convertFromDecToHex() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("5A3", exprParser.convertExpression("1443", 10, 16));
    }

    @Test
    public void convertFromDecToOctal() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("11", exprParser.convertExpression("9", 10, 8));
    }

    @Test
    public void convertFromBinaryToDec() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("7", exprParser.convertExpression("111", 2, 10));
    }

    @Test
    public void convertFromBinaryToHex() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("1F", exprParser.convertExpression("11111", 2, 16));
    }

    @Test
    public void convertFromBinaryToOctal() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("37", exprParser.convertExpression("11111", 2, 8));
    }

    @Test
    public void convertFromHexToDec() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("1443", exprParser.convertExpression("5A3", 16, 10));
    }

    @Test
    public void convertFromHexToOctal() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("37", exprParser.convertExpression("1F", 16, 8));
    }

    @Test
    public void convertFromHexToBinary() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("10110100011", exprParser.convertExpression("5A3", 16, 2));
    }
    @Test
    public void convertFromOctalToBinary() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("11111", exprParser.convertExpression("37", 8, 2));
    }
    @Test
    public void convertFromOctalToHex() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("1F", exprParser.convertExpression("37", 8, 16));
    }
    @Test
    public void convertFromOctalToDec() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("31", exprParser.convertExpression("37", 8, 10));
    }

    @Test
    public void convertExpression() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("5A3+5A3*5A3*(5A3+5A3)", exprParser
                .convertExpression("1443+1443*1443*(1443+1443)", 10, 16));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("0", exprParser.calcExpression(new StringBuilder("2/0")));
    }

    @Test
    public void calcSin() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals(Double.toString(Math.sin(1)), exprParser.calcExpression(new StringBuilder("sin(1)")));
    }

    @Test
    public void calcCos() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals(Double.toString(Math.cos(1)), exprParser.calcExpression(new StringBuilder("cos(1)")));
    }

    @Test
    public void calcTan() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals(Double.toString(Math.tan(1)), exprParser.calcExpression(new StringBuilder("tan(1)")));
    }

    @Test
    public void calcPow() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("2.1742289474660241", exprParser.calcExpression(new StringBuilder("pow(1.3333,2.7)")));
    }

    @Test
    public void calcSqrt() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals(Double.toString(Math.sqrt(50.253)), exprParser.calcExpression(new StringBuilder("sqrt(50.253)")));
    }

    @Test
    public void testOverflow() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("1654654654654654654654645654654654654654654632443242342343", exprParser
                .calcExpression(new StringBuilder("1654654654654654654654645654654654654654654632443242342342+1")));
    }

    @Test
    public void calcExpressionWithBrackets() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("25.6146892868013238", exprParser
                .calcExpression(new StringBuilder("123.5555/(2.333+2.7777)*cos(0.2)+(-10+3.555)*pow(0.333,4)+sqrt(4)")));

    }

    @Test
    public void calcHexExpression() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("5A3", exprParser
                .calcExpression(new StringBuilder("5A3*(5A3+5A3)/5A3-5A3"), 16));
    }

    @Test
    public void calcBinaryExpression() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("10110100011", exprParser
                .calcExpression(new StringBuilder("10110100011*(10110100011+10110100011)/10110100011-10110100011"), 2));
    }

    @Test
    public void calcOctalExpression() {
        ExpressionParser exprParser = new ExpressionParser();
        assertEquals("2643", exprParser
                .calcExpression(new StringBuilder("2643*(2643+2643)/2643-2643")));
    }
}