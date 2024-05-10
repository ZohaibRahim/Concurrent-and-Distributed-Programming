package package_zohaibrahim;

public class NodeStartLCR extends NodeLCR implements Runnable {

    public NodeStartLCR(int id, int value) {
        super(id, value);
    }

    @Override
    public void run() {
        System.out.println("Start");
        String initialMessage = String.valueOf(getValue());
        getRight().receiveMessage(initialMessage);

        while(true){
            if(getMessages().peek() != null){
                String s = String.valueOf(getValue());
                System.out.println("Message " + s + " received by Node " + getId() + " with value " + getValue());
                getLeft().receiveMessage(s);
            }

            // Termination condition: Node elected as leader
            if (isLeaderElected()) {
                // Terminate further processing
                break;
            }

            // Add a small delay to avoid busy-waiting and give other threads a chance
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}