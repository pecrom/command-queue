package com.task.command.parser.exception;

/**
 * Exception when command is not found
 */
public class CommandNotFoundException extends RuntimeException {

    /**
     * Constructor
     */
    public CommandNotFoundException() {
        super("Command not found");
    }
}
