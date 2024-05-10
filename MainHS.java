package package_zohaibrahim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainHS {
    public static void main(String[] args) {
        ArrayList<NodeHS> nodes = new ArrayList<>();
        NodeStartHS nodeStartHS = new NodeStartHS(0, 0);

        // Create nodes with random values and IDs
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }
        Collections.shuffle(ids);

        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt(100);  // Assign a random value between 0 and 99
            NodeHS node = new NodeHS(ids.get(i), rand); // Using shuffled IDs
            nodes.add(node);
        }

        // Set left and right neighbors for HS algorithm
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setLeft(nodes.get((i - 1 + nodes.size()) % nodes.size())); // Circular left neighbor
            nodes.get(i).setRight(nodes.get((i + 1) % nodes.size())); // Circular right neighbor
        }

        NodeHS firstNode = nodes.get(0); // Assuming nodes list is not empty
        nodeStartHS.setRight(firstNode);

        // Display and compare values of nodes
        for (NodeHS node : nodes) {
            System.out.println("Node " + node.getId() + " Value: " + node.getValue());
        }

        // Start threads for HS algorithm
        for (NodeHS node : nodes) {
            Thread thread = new Thread((Runnable) node);
            thread.start();
        }

        // Start the thread for the starting node
        Thread thread = new Thread(nodeStartHS);
        thread.start();
    }
}
