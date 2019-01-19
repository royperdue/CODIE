package com.codie.simulation.ui.view.graphview;

/**
 *
 */

public final class Conditions {
    private Conditions() {
    }

    static <T> T isNonNull(T object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }

        return object;
    }
}
