import java.util.concurrent.atomic.AtomicInteger;

public class BakeryAlgorithm {
    private static final int NoT = 20; // no. of threads
    private static final int[] number = new int[NoT]; // Array to store ticket numbers
    private static final boolean[] choosing = new boolean[NoT]; // Array to indicate if a thread is choosing its ticket number
    private static final AtomicInteger criticalSectionCounter = new AtomicInteger(0); // Counter to track critical section entries

    public static void main(String[] args) {
        Thread[] threads = new Thread[NoT];

        // Create and start N threads
        for (int i = 0; i < NoT; i++) {
            final int threadID = i;
            threads[i] = new Thread(() -> {
                enterCriticalSection(threadID); // Call function to enter the critical section
                try {
                    Thread.sleep(100); // Simulate some work in the critical section
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                exitCriticalSection(threadID); // Call function to exit the critical section
            });
            threads[i].start();
        }
    }

    private static void enterCriticalSection(int i) {
        choosing[i] = true;
        number[i] = max() + 1; // Choose the next available ticket number
        choosing[i] = false;

        // Iterate over all threads to ensure fair ordering based on Bakery algorithm
        for (int j = 0; j < NoT; j++) {
            while (choosing[j]) { /* Wait for other thread to finish choosing its ticket */ }

            // Wait if either of the following conditions are met:
            // 1. Thread j has a lower ticket number than thread i
            // 2. Thread j has the same ticket number as thread i but a higher thread ID
            while (choosing[j] || (number[j] != 0 && (number[j] < number[i] || (number[j] == number[i] && j < i)))) {
                try {
                    Thread.sleep(1); // Introduce a short delay to avoid busy waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void exitCriticalSection(int i) {
        number[i] = 0; // Reset ticket number to indicate thread is no longer in critical section
        System.out.println("Thread ID: " + i + ", Bakery Number: " + number[i] + ", Critical Section Counter: " + criticalSectionCounter.incrementAndGet());
    }

    private static int max() {
        int max = number[0];
        // Find the maximum ticket number among all threads
        for (int i = 1; i < NoT; i++) {
            if (number[i] > max) {
                max = number[i];
            }
        }
        return max;
    }
}


/*
OUTPUT:

Thread ID: 0, Bakery Number: 0, Critical Section Counter: 1
Thread ID: 1, Bakery Number: 0, Critical Section Counter: 2
Thread ID: 2, Bakery Number: 0, Critical Section Counter: 3
Thread ID: 3, Bakery Number: 0, Critical Section Counter: 4
Thread ID: 4, Bakery Number: 0, Critical Section Counter: 5
Thread ID: 5, Bakery Number: 0, Critical Section Counter: 6
Thread ID: 6, Bakery Number: 0, Critical Section Counter: 7
Thread ID: 7, Bakery Number: 0, Critical Section Counter: 8
Thread ID: 8, Bakery Number: 0, Critical Section Counter: 9
Thread ID: 9, Bakery Number: 0, Critical Section Counter: 10
Thread ID: 10, Bakery Number: 0, Critical Section Counter: 11
Thread ID: 11, Bakery Number: 0, Critical Section Counter: 12
Thread ID: 12, Bakery Number: 0, Critical Section Counter: 13
Thread ID: 13, Bakery Number: 0, Critical Section Counter: 14
Thread ID: 14, Bakery Number: 0, Critical Section Counter: 15
Thread ID: 15, Bakery Number: 0, Critical Section Counter: 16
Thread ID: 16, Bakery Number: 0, Critical Section Counter: 17
Thread ID: 17, Bakery Number: 0, Critical Section Counter: 18
Thread ID: 18, Bakery Number: 0, Critical Section Counter: 19
Thread ID: 19, Bakery Number: 0, Critical Section Counter: 20
 */