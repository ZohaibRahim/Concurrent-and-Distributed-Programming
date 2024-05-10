public class CustomThread extends Thread {
    private static final int threadCount = 10; // Number of threads
    private static int[] threadRunCount = new int[threadCount];  // Array to track the run count for each thread
    private final int threadID; // Unique identifier for each thread

    // Constructor to initialize the thread ID
    public CustomThread(int threadID) {
        this.threadID = threadID;
    }

    // Run method that defines the behavior of the thread
    public void run() {
        while (true) {
            System.out.println("Thread " + threadID + " is running. Total runs: " + ++threadRunCount[threadID]);

            // Calculate and print the sum of run counts across all threads
            int sum = calculateSum();
            System.out.println("Sum of run counts: " + sum);
        }
    }

    // Static synchronized method to calculate the sum of run counts for all threads
    public static synchronized int calculateSum() {
        int sum = 0;
        for (int count : threadRunCount) {
            sum += count;
        }
        return sum;
    }
}
