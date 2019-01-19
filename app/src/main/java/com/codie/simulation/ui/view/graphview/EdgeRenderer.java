package com.codie.simulation.ui.view.graphview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *
 */
public interface EdgeRenderer {
    void render(Canvas canvas, Graph graph, Paint paint);
}
