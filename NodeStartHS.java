package package_zohaibrahim;

public class NodeStartHS extends NodeHS implements Runnable {

    public NodeStartHS(int id, int value) {
        super(id, value);
    }

    @Override
    public void run() {
        System.out.println("Start");
        String initialMessage = String.valueOf(getValue());

        // Initialize the HS algorithm by sending messages in both directions
        getRight().receiveMessage(initialMessage, true); // Outgoing message
        getLeft().receiveMessage(initialMessage, true);  // Outgoing message
    }
}
