package com.codie.simulation.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.codie.simulation.MyApplication;
import com.codie.simulation.R;
import com.codie.simulation.data.model.DataObject;
import com.codie.simulation.data.model.DataObjectDao;
import com.codie.simulation.data.model.Pointer;
import com.codie.simulation.exception.UnsupportedModuleException;
import com.codie.simulation.ui.activity.MainActivity;
import com.codie.simulation.ui.fragment.base.BaseFragment;
import com.codie.simulation.ui.help.HelpOptionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.codie.simulation.ui.view.graphview.BaseGraphAdapter;
import com.codie.simulation.ui.view.graphview.Graph;
import com.codie.simulation.ui.view.graphview.GraphAdapter;
import com.codie.simulation.ui.view.graphview.GraphView;
import com.codie.simulation.ui.view.graphview.Node;
import com.codie.simulation.ui.view.graphview.NodeData;
import com.codie.simulation.ui.view.graphview.energy.FruchtermanReingoldAlgorithm;
import com.codie.simulation.util.GraphAdjacencyList;


public class HomeFragment extends BaseFragment {
    private int nodeCount = 1;
    private Node currentNode;
    private BaseGraphAdapter<ViewHolder> adapter;
    private AppCompatButton diagramButton;
    private AppCompatButton addNodeButton;
    private AppCompatButton removeNodeButton;
    private GraphView graphView;
    private Graph graph;

    public HomeFragment() {
        super(R.string.navigation_fragment_home, R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void ready(View view) throws UnsupportedModuleException {
        if (navigation.getVisibility() == View.GONE)
            navigation.setVisibility(View.VISIBLE);

        setUp(view);
    }

    @Override
    protected void setUp(final View view) {
        graphView = view.findViewById(R.id.graph);
        diagramButton = view.findViewById(R.id.diagramButton);
        diagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CREATE-GRAPH-CALLED");
                graph = createGraph(view);
            }
        });
    }


    @Override
    protected void fillHelpOptionAdapter(HelpOptionAdapter adapter) {

    }

    @Override
    public void reconnected() {

    }

    @Override
    public Graph createGraph(View view) {
        final Graph graph = new Graph();
        Map<Node, Node> nodePairs = new HashMap<>();
        Node firstNode = null;
        Node secondNode = null;
        List<Pointer> pointers = MyApplication.getDaoSession().getPointerDao().queryBuilder().list();
        System.out.println("SIZE-POINTERS-LIST: " + pointers.size());

        for (int i = 0; i < pointers.size(); i++) {
            DataObject firstDataObject = MyApplication.getDaoSession().getDataObjectDao()
                    .queryBuilder().where(DataObjectDao.Properties.ObjectId.eq(pointers.get(i).getObjectId()))
                    .list().get(0);

            System.out.println("FIRST-DATA-OBJECT-ID: " + firstDataObject.getObjectId());

            if (firstDataObject != null) {
                for (int n = 0; n < firstDataObject.getPointers().size(); n++) {
                    NodeData nodeDataOne = new NodeData(firstDataObject.getObjectId(), firstDataObject.getName(), firstDataObject.getConfidenceValue());

                    firstNode = graph.getNode(nodeDataOne);

                    if (firstNode == null) {
                        firstNode = new Node(nodeDataOne);
                    }

                    if (n < firstDataObject.getPointers().size()) {
                        DataObject secondDataObject = MyApplication.getDaoSession().getDataObjectDao()
                                .queryBuilder().where(DataObjectDao.Properties.ObjectId.eq(firstDataObject.getPointers().get(n).getObjectId()))
                                .list().get(0);

                        if (secondDataObject != null) {
                            NodeData nodeDataTwo = new NodeData(secondDataObject.getObjectId(), secondDataObject.getName(), secondDataObject.getConfidenceValue());

                            secondNode = graph.getNode(nodeDataTwo);

                            if (secondNode == null) {
                                secondNode = new Node(nodeDataTwo);
                            }
                        }
                    }

                    if (firstNode != null && secondNode != null) {
                        graph.addEdge(firstNode, secondNode);
                    }
                }
            }
        }

        addNodeButton = view.findViewById(R.id.addNodeButton);
        addNodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNode != null && graph != null) {
                    final Node newNode = new Node(getNodeText());
                    graph.addEdge(currentNode, newNode);
                }
            }
        });

        removeNodeButton = view.findViewById(R.id.removeNodeButton);
        removeNodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNode != null && graph != null) {
                    graph.removeNode(currentNode);
                    currentNode = null;
                }
            }
        });

        adapter = new BaseGraphAdapter<ViewHolder>(((MainActivity)owner), R.layout.node, graph) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                viewHolder.textView.setText(data.toString());
            }
        };
        setAlgorithm(adapter);

        graphView.setAdapter(adapter);
        graphView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentNode = adapter.getNode(position);
                Snackbar.make(graphView, "Clicked on " + currentNode.getData().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return graph;
    }

    @Override
    public void setAlgorithm(GraphAdapter adapter) {
        adapter.setAlgorithm(new FruchtermanReingoldAlgorithm(1000));
    }

    public static class ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.textView);
        }
    }

    private String getNodeText() {
        return "Node " + nodeCount++;
    }
}
