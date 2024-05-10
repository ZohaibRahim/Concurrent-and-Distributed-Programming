public class BasicThreadsMain {
    private static final int threadCount = 10; // Number of threads to create

    public static void main(String[] args) {
        // Array to hold instances of CustomThread threads
        CustomThread[] threads = new CustomThread[threadCount];

        // Create and start CustomThread threads
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomThread(i);
            threads[i].start();
        }
    }
}
