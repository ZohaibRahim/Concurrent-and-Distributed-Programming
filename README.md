**Title:** Bakery Algorithm in Java

**Description:**
This Java program demonstrates the Bakery Algorithm, a classic mutual exclusion algorithm used in concurrent systems to ensure that multiple threads can access a critical section without interference. The algorithm assigns each thread a unique ticket number, and threads wait based on their ticket numbers to enter the critical section, ensuring fairness and preventing deadlock.

**Key Features:**
- **Ticket Number Assignment:** Threads acquire a ticket number before entering the critical section, ensuring that the order of entry is based on these numbers.
- **Atomic Operations:** The use of `AtomicInteger` and synchronized data structures ensures thread safety and prevents race conditions when accessing shared variables.
- **Fairness:** The algorithm guarantees fairness by prioritizing threads with lower ticket numbers, preventing starvation and ensuring that all threads eventually gain access to the critical section.
- **Critical Section Counter:** A counter tracks the number of times threads enter the critical section, providing insights into the execution flow and demonstrating mutual exclusion.

**Usage:**
Users can run the program to observe the Bakery Algorithm in action, with each thread entering and exiting the critical section based on its assigned ticket number. The console output displays the thread ID, bakery number (ticket number), and the critical section counter, providing a clear visualization of the algorithm's behavior.

**Output Example:**
```
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
```

**Conclusion:**
By studying and experimenting with the Bakery Algorithm, users can gain insights into mutual exclusion techniques and their applications in concurrent programming. This code provides a user-friendly demonstration of the algorithm's functionality, making it accessible for educational and practical purposes.
