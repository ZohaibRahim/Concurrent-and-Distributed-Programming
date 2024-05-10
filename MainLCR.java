package package_zohaibrahim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainLCR {
    public static void main(String[] args) {
        ArrayList<NodeLCR> nodes = new ArrayList<>();
        NodeStartLCR nodeStartLCR = new NodeStartLCR(0,0);



        // Create nodes with random values and IDs
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }
        Collections.shuffle(ids);

        for (int i = 0; i < 10; i++) {
            int rand  = new Random().nextInt(100);  // Assign a random value between 0 and 99
            NodeLCR node = new NodeLCR(i + 1, rand); // Assuming random values from 0 to 99
            nodes.add(node);
        }

        // Set left and right neighbors for LCR algorithm
        nodes.get(0).setLeft(nodes.get(nodes.size() - 1));
        nodes.get(nodes.size() - 1).setRight(nodes.get(0));
        nodes.get(0).setRight(nodes.get(1));
        nodes.get(9).setLeft(nodes.get(8));
        for (int i = 1; i < nodes.size()-1; i++) {
            nodes.get(i).setRight(nodes.get(i+1));
            nodes.get(i).setLeft(nodes.get(i-1));
        }

        NodeLCR firstNode = nodes.get(0); // Assuming nodes list is not empty
        nodeStartLCR.setRight(firstNode);

        // Display and compare values of nodes
        for (NodeLCR node : nodes) {
            System.out.println("Node " + node.getId() + " Value: " + node.getValue());
        }

        // Start threads for LCR algorithm
        for (NodeLCR node : nodes) {
            Thread thread = new Thread((Runnable) node);
            thread.start();
        }

        // Start the thread for the starting node
        Thread thread = new Thread(nodeStartLCR);
        thread.start();

    }
}
