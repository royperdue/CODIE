package com.codie.simulation.util;

import com.codie.simulation.ui.view.graphview.Node;
import com.codie.simulation.ui.view.graphview.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class GraphAdjacencyList {
    private Map<Long, List<Node>> adjacencyList = new HashMap<Long, List<Node>>();
    private List<Node> nodeList = null;

    public void addNode(Long id, Node node) {
        if (!adjacencyList.containsKey(id)) {
            nodeList = new ArrayList<Node>();
            nodeList.add(node);
            adjacencyList.put(id, nodeList);
        } else {
            nodeList = adjacencyList.get(id);
            nodeList.add(node);
        }
    }

    public boolean containsNode(long id) {
        boolean containsNode = false;
        Iterator iterator = adjacencyList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry nodeEntry = (Map.Entry) iterator.next();
            nodeList = (List<Node>) nodeEntry.getValue();

            for (int i = 0; i < nodeList.size(); i++) {
                NodeData nodeData = (NodeData) nodeList.get(i).getData();

                if (nodeData.getId() == id) {
                    containsNode = true;
                    break;
                }
            }
        }

        return containsNode;
    }

    public List<Node> getListAssociatedNodes(Long id) {
        List<Node> nodes = new ArrayList<Node>();

        if (!adjacencyList.containsKey(id)) {
            nodes = adjacencyList.get(id);

            return nodes;
        } else
            return null;
    }

    public List<Node> getAllElements() {
        List<Node> nodes = new ArrayList<Node>();
        Iterator iterator = adjacencyList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry elementData = (Map.Entry) iterator.next();
            nodeList = (List<Node>) elementData.getValue();

            for (int i = 0; i < nodeList.size(); i++) {
                nodes.add(nodeList.get(i));
            }
        }

        return nodes;
    }

    public Map<Long, List<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public String toString() {
        String element = "";

        Iterator iterator = adjacencyList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry spectrum = (Map.Entry) iterator.next();
            nodeList = (List<Node>) spectrum.getValue();

            for (int i = 0; i < nodeList.size(); i++) {
                //element = element + (String) spectrum.getKey() + " " + elementList.get(i)
                        //.getWavelength() + " " + elementList.get(i).getIntensity() + " ";
            }

            element = element + "\n\n";
        }

        return element;
    }
}
