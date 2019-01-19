package com.codie.simulation.ui.view.graphview;

/**
 *
 */

public interface NodeObserver {
    void notifyDataChanged(Node node);

    void notifyNodeAdded(Node node);

    void notifyNodeRemoved(Node node);

    void notifyInvalidated();
}
