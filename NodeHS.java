package package_zohaibrahim;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
public class NodeHS implements Runnable {
    private NodeHS left;
    private NodeHS right;
    private int id;
    private int value;
    private volatile boolean terminated = false;
    private volatile boolean leaderElected = false;
    private BlockingQueue<String> messages;

    public NodeHS(int id, int value) {
        messages = new ArrayBlockingQueue<>(10);
        this.id = id;
        this.value = value;
    }

    public void setLeft(NodeHS left) {
        this.left = left;
    }

    public void setRight(NodeHS right) {
        this.right = right;
    }

    public void terminate() {
        terminated = true;
    }
    
    private void setLeaderElected() {
        leaderElected = true;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void receiveMessage(String mess, boolean isOutgoing) {
        int messageID = Integer.parseInt(mess);

        if ((isOutgoing && messageID > id) || (!isOutgoing && messageID < id)) {
            if (isOutgoing) {
                right.sendMessage(mess, isOutgoing);
            } else {
                left.sendMessage(mess, isOutgoing);
            }
        }
    }

    private String waitForMessage(boolean isOutgoing) {
        while (!terminated) {
            String message = messages.poll();

            if (message != null) {
                int messageID = Integer.parseInt(message);

                if ((isOutgoing && messageID < id) || (!isOutgoing && messageID > id)) {
                    return message;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void sendMessage(String mess, boolean isOutgoing) {
        messages.add(mess);
    }

    public void run() {
        while (!terminated) {
            sendMessage(String.valueOf(id), true);  // Outgoing message to the right
            sendMessage(String.valueOf(id), false); // Outgoing message to the left

            String rightMessage = waitForMessage(true);
            String leftMessage = waitForMessage(false);

            if (rightMessage != null && leftMessage != null) {
                int rightID = Integer.parseInt(rightMessage);
                int leftID = Integer.parseInt(leftMessage);

                if (rightID == id && leftID == id) {
                    System.out.println("Node " + id + " is elected as the leader.");
                    setLeaderElected();
                    break;
                }
            }
        }
    }

    public NodeHS getLeft(){return left;}
    public NodeHS getRight(){return right;}
    private String getMessage() {
        try {
            return messages.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
