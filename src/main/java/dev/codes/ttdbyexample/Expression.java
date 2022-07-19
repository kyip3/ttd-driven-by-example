package dev.codes.ttdbyexample;

public interface Expression {
    Money reduce(Bank bank, String toCurrency);
}
