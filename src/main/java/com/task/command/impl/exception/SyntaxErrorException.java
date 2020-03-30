package com.task.command.impl.exception;

/**
 * Thrown when syntax of command is wrong
 */
public class SyntaxErrorException extends RuntimeException {

    /**
     * Constructor
     */
    public SyntaxErrorException() {
        super("Syntax error!");
    }
}
