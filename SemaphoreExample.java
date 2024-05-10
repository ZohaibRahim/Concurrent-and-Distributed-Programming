package Q2;

public class SemaphoreExample {
    private static final int threads = 15;
    public static void main(String[] args) {
        for (int i =0 ; i < threads; i++) {
            final int threadId = i;
            new Thread(() -> sharedFile.accessFiles(threadId)).start();
        }
    }
}
