package Q1;

public class MyThreads extends Thread {
    public void run() {
        int i=1;
        while (true) {
            System.out.println("Thread no. " + i+ " is running...");
            i++;
        }
    }

    public static void main(String[] args) {
        int numberOfBasicThreads = 0;

        try {
            while (true) {
                new MyThreads().start();
                numberOfBasicThreads++;
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory after creating " + numberOfBasicThreads + " basic threads.");
        }
    }
}
