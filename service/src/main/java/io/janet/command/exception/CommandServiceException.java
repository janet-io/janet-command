package io.janet.command.exception;

import io.janet.Command;
import io.janet.JanetException;

public class CommandServiceException extends JanetException {

    public CommandServiceException(Command action, Throwable cause) {
        super("Something went wrong with " + action.getClass().getSimpleName(), cause);
    }
}
