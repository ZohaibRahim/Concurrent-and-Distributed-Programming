public class ReentrantMain {
    public static void main(String[] args) {
        CustomReentrantLock lock = new CustomReentrantLock();

        // Thread 1
        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");

            // Acquiring the lock again within the same thread
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock again.");

            // Release the lock
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock.");

            // Release the lock again
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock again.");
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");

            // Release the lock
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock.");
        });

        // Start the threads
        thread1.start();
        thread2.start();
    }
}