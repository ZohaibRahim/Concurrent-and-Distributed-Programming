public class BasicLockMain {
    private static final int threadCount = 10;  // Number of threads to create

    public static void main(String[] args) {
        CustomBasicLock[] threads = new CustomBasicLock[threadCount]; // Array to hold instances of CustomBasicLock threads

        // Create and start CustomBasicLock threads
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomBasicLock(i);
            threads[i].start();
        }
    }
}
