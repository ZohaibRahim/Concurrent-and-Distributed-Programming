**Title:** Non-Preemptive Peterson Algorithm in Java

**Description:**
This Java program demonstrates the implementation of the Non-Preemptive Peterson Algorithm, a classic solution for mutual exclusion in concurrent systems. The algorithm ensures that multiple threads can access a critical section without interference, thus preventing race conditions and maintaining program correctness.

**Key Features:**
- **Atomic Operations:** The algorithm utilizes `AtomicIntegerArray` from the `java.util.concurrent.atomic` package to ensure atomicity and thread safety when accessing shared variables.
- **Critical Section Entry and Exit:** The `enterCriticalSection` and `exitCriticalSection` methods facilitate thread entry and exit from the critical section, respectively.
- **Priority Handling:** Threads are granted access to the critical section based on their assigned priorities, preventing lower-priority threads from preempting higher-priority ones.
- **Thread Simulation:** Each thread simulates performing work in the critical section by sleeping for a short duration before exiting.

**Usage:**
Users can instantiate the `PetersonNPAlgorithm` class with the desired number of nodes (threads) and observe the algorithm in action by running the `main` method. The console output demonstrates the sequence of threads entering and exiting the critical section, showcasing the non-preemptive behavior of the algorithm.

**Output Example:**
```
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
```

**Conclusion:**
By studying and experimenting with the Non-Preemptive Peterson Algorithm, users can gain insights into mutual exclusion techniques and their applications in concurrent programming. This code provides a clear and concise demonstration of the algorithm's functionality, making it accessible for educational and practical purposes.
