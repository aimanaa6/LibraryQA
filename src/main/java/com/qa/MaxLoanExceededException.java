package com.qa;

public class MaxLoanExceededException extends Exception {
    public MaxLoanExceededException(String message) {
        super(message);
    }

    public MaxLoanExceededException() {
        super("Maximum loan limit exceeded");
    }
}
