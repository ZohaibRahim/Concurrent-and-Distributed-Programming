import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonNPAlgorithm {
    private final int nodes; // Number of nodes
    private final AtomicIntegerArray turn; // Array to hold turn values
    private final AtomicIntegerArray flag; // Array to hold flag values

    public PetersonNPAlgorithm(int nodes) {
        this.nodes = nodes;
        turn = new AtomicIntegerArray(nodes);
        flag = new AtomicIntegerArray(nodes);
    }

    // Method to enter the critical section
    public void enterCriticalSection(int i) {
        System.out.println("Thread " + i + " entering the critical section");
        for (int k = 1; k < nodes; k++) {
            flag.set(i, k);
            turn.set(k, i);
            // Wait until no other thread has higher priority and turn[k] is not i
            while (existsHighPriority(k, i) && turn.get(k) == i) {
                // Busy wait
            }
        }
    }

    // Method to exit the critical section
    public void exitCriticalSection(int i) {
        System.out.println("Thread " + i + " exiting the critical section");
        flag.set(i, 0);
    }

    // Method to check if there exists a thread with higher priority
    private boolean existsHighPriority(int k, int i) {
        for (int j = 0; j < nodes; j++) {
            if (j != i && flag.get(j) >= k) {
                return true;
            }
        }
        return false;
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        int n = 5; // Number of nodes
        PetersonNPAlgorithm petersonNP = new PetersonNPAlgorithm(n);

        // Create and start threads
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                petersonNP.enterCriticalSection(threadId);
                try {
                    Thread.sleep(100); // Simulate some work in the critical section
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                petersonNP.exitCriticalSection(threadId);
            });
            threads[i].start();
        }
    }
}

/*
OUTPUT:

Thread 4 entering the critical section
Thread 1 entering the critical section
Thread 2 entering the critical section
Thread 0 entering the critical section
Thread 3 entering the critical section
Thread 4 exiting the critical section
Thread 1 exiting the critical section
Thread 2 exiting the critical section
Thread 0 exiting the critical section
Thread 3 exiting the critical section
 */