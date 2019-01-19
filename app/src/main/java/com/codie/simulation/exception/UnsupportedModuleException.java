package com.codie.simulation.exception;

/**
 * Checked exception signifying a module is not supported on the connected MetaWear board
 *
 */
public class UnsupportedModuleException extends Exception {
    private static final long serialVersionUID = -2869845467241050695L;

    /**
     * Creates an exception with the given message
     * @param msg    Message to accompany the exception
     */
    public UnsupportedModuleException(String msg) {
        super(msg);
    }
}