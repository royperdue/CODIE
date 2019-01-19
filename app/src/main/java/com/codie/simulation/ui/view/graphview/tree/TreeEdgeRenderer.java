package com.codie.simulation.ui.view.graphview.tree;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.Node;

import java.util.List;

import com.codie.simulation.ui.view.graphview.EdgeRenderer;

import static com.codie.simulation.ui.view.graphview.tree.BuchheimWalkerConfiguration.ORIENTATION_BOTTOM_TOP;
import static com.codie.simulation.ui.view.graphview.tree.BuchheimWalkerConfiguration.ORIENTATION_LEFT_RIGHT;
import static com.codie.simulation.ui.view.graphview.tree.BuchheimWalkerConfiguration.ORIENTATION_RIGHT_LEFT;
import static com.codie.simulation.ui.view.graphview.tree.BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM;

/**
 *
 */
public class TreeEdgeRenderer implements EdgeRenderer {

    private BuchheimWalkerConfiguration configuration;
    private Path linePath = new Path();

    TreeEdgeRenderer(BuchheimWalkerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void render(Canvas canvas, Graph graph, Paint paint) {
        List<Node> nodes = graph.getNodes();

        for (Node node : nodes) {
            List<Node> children = graph.successorsOf(node);

            for (Node child : children) {
                linePath.reset();
                switch (configuration.getOrientation()) {
                    case ORIENTATION_TOP_BOTTOM:
                        // position at the middle-top of the child
                        linePath.moveTo(child.getX() + (child.getWidth() / 2),  child.getY());
                        // draws a line from the childs middle-top halfway up to its parent
                        linePath.lineTo( child.getX() + (child.getWidth() / 2),  child.getY() - (configuration.getLevelSeparation() / 2));
                        // draws a line from the previous point to the middle of the parents width
                        linePath.lineTo( node.getX() + (node.getWidth() / 2),
                                child.getY() - configuration.getLevelSeparation() / 2);


                        // position at the middle of the level separation under the parent
                        linePath.moveTo( node.getX() + (node.getWidth() / 2),
                                child.getY() - configuration.getLevelSeparation() / 2);
                        // draws a line up to the parents middle-bottom
                        linePath.lineTo( node.getX() + (node.getWidth() / 2),
                                node.getY() + node.getHeight());

                        break;
                    case ORIENTATION_BOTTOM_TOP:
                        linePath.moveTo( child.getX() + (child.getWidth() / 2),  child.getY() + child.getHeight());
                        linePath.lineTo( child.getX() + (child.getWidth() / 2),  child.getY() + child.getHeight() + (configuration.getLevelSeparation() / 2));
                        linePath.lineTo( node.getX() + (node.getWidth() / 2),
                                child.getY() + child.getHeight() + configuration.getLevelSeparation() / 2);

                        linePath.moveTo( node.getX() + (node.getWidth() / 2),
                                child.getY() + child.getHeight() + configuration.getLevelSeparation() / 2);
                        linePath.lineTo( node.getX() + (node.getWidth() / 2),
                                node.getY() + node.getHeight());

                        break;
                    case ORIENTATION_LEFT_RIGHT:
                        linePath.moveTo( child.getX(),  child.getY() + child.getHeight() / 2);
                        linePath.lineTo( child.getX() - (configuration.getLevelSeparation() / 2),  child.getY() + child.getHeight() / 2 );
                        linePath.lineTo( child.getX() - (configuration.getLevelSeparation() / 2),
                                node.getY() + node.getHeight() / 2);

                        linePath.moveTo( child.getX() - (configuration.getLevelSeparation() / 2),
                                node.getY() + node.getHeight() / 2);
                        linePath.lineTo( node.getX() + (node.getWidth()),
                                node.getY() + node.getHeight() / 2);

                        break;
                    case ORIENTATION_RIGHT_LEFT:
                        linePath.moveTo( child.getX() + child.getWidth(),  child.getY() + child.getHeight() / 2);
                        linePath.lineTo( child.getX() + child.getWidth() + (configuration.getLevelSeparation() / 2),  child.getY() + child.getHeight() / 2 );
                        linePath.lineTo( child.getX() + child.getWidth() + configuration.getLevelSeparation() / 2,
                                node.getY() + node.getHeight() / 2);

                        linePath.moveTo( child.getX() + child.getWidth() + configuration.getLevelSeparation() / 2,
                                node.getY() + node.getHeight() / 2);
                        linePath.lineTo( node.getX() + (node.getWidth()),
                                node.getY() + node.getHeight() / 2);

                }

                canvas.drawPath(linePath, paint);
            }
        }
    }
}
