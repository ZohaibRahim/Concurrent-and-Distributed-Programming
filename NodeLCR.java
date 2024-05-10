package package_zohaibrahim;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class NodeLCR implements Runnable {
    private NodeLCR left;
    private NodeLCR right;
    private int id;
    private int value;
    private BlockingQueue<String> messages;

    public NodeLCR(int id, int value) {
        messages = new ArrayBlockingQueue<>(10);
        this.id = id;
        this.value = value;
    }

    public void receiveMessage(String mess) {
        try {
            int messageValue = Integer.parseInt(mess);
            messages.remove(mess);

            // LCR Algorithm: Forward own ID and value to the left neighbor
            if (messageValue > value ) {
                left.sendMessage(mess);
            } else if (messageValue < value ) {
                // Discard the message
            } else {
                // Termination condition: Node elected as leader
                System.out.println("Node " + id + " with value " + value + " is elected as the leader.");
                right.sendMessage(String.valueOf(id) + "," + String.valueOf(value));  // Forward the message to avoid getting stuck
                setLeaderElected();  // Mark the leader election as complete
                return;  // Terminate further processing
            }
        } catch (NumberFormatException e) {
            // Regular message
            messages.add(mess);
        }
    }

    public void sendMessage(String mess) {
        messages.add(mess);
    }

    private volatile boolean terminated = false;

    public void terminate() {
        terminated = true;
    }

    private volatile boolean leaderElected = false;

    public boolean isLeaderElected() {
        return leaderElected;
    }

    private void setLeaderElected() {
        leaderElected = true;
    }

    public void run() {
        while (!terminated) {
            messages.add(String.valueOf(this.value));
            if (!messages.isEmpty()) {
                String s = null;
                try {
                    s = messages.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Termination condition: Node elected as leader
                if (Integer.parseInt(s.split(",")[0]) == id && Integer.parseInt(s.split(",")[1]) == value) {
                    System.out.println("Node " + id + " with value " + value + " is elected as the leader.");
                    right.sendMessage(s);  // Forward the message to avoid getting stuck
                    setLeaderElected();  // Mark the leader election as complete
                    break;
                }

                // LCR Algorithm: Forward own ID and value to the left neighbor
                left.receiveMessage(s);

            }
        }
    }

    public void setLeft(NodeLCR left) {
        this.left = left;
    }

    public void setRight(NodeLCR right) {
        this.right = right;
    }

    public BlockingQueue<String> getMessages() {
        return messages;
    }
    public NodeLCR getLeft() {
        return left;
    }

    public NodeLCR getRight() {
        return right;
    }

    public int getId() {
        return id;
    }
    public int getValue() {
        return value;
    }
}

