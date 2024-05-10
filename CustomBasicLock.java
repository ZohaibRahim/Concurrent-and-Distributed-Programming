public class CustomBasicLock extends Thread {
    private static final int threadCount = 10; // Number of threads
    private static int[] threadRunCount = new int[threadCount];// Array to track the run count for each thread
    private boolean locked = false;// Flag to indicate whether the lock is currently held
    private final int threadID;// Unique identifier for each thread
    public CustomBasicLock(int threadID) {
        this.threadID = threadID;
    }

    //method to acquire the lock
    private synchronized void lock() {
        // Wait until the lock is available
        while (locked) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Restore interrupted status and continue waiting
                Thread.currentThread().interrupt();
            }
        }
        locked = true; // Acquire the lock
    }

    // Method to release the lock
    private synchronized void unlock() {
        // Release the lock and notify waiting threads
        locked = false;
        notify();
    }

    // Run method that defines the behavior of the thread
    public void run() {
        while (true) {
            lock();// Acquire the lock before executing the critical section
            try {
                System.out.println("Thread " + threadID + " is running. Total runs: " + ++threadRunCount[threadID]);

                // Calculate and print the sum of run counts across all threads
                int sum = calculateSum();
                System.out.println("Sum of run counts: " + sum);
            } finally {
                unlock(); // Release the lock in a finally block to ensure it is released even if an exception occurs
            }
        }
    }

    // Method to calculate the sum of run counts for all threads
    public static synchronized int calculateSum() {
        int sum = 0;
        for (int count : threadRunCount) {
            sum += count;
        }
        return sum;
    }
}
