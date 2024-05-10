class CustomReentrantLock {
    private boolean locked = false;
    private Thread currentThread = null;
    private int lockCount = 0;

    // Method to acquire the lock
    public synchronized void lock() {
        Thread callingThread = Thread.currentThread();

        // Check if the lock is already held by another thread
        while (locked && currentThread != callingThread) {
            try {
                // Wait until the lock is released
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Acquire the lock
        locked = true;
        currentThread = callingThread;
        lockCount++;
    }

    // Method to release the lock
    public synchronized void unlock() {
        // Check if the current thread is the one holding the lock
        if (Thread.currentThread() == currentThread) {
            lockCount--;

            // Release the lock only when the count is zero
            if (lockCount == 0) {
                locked = false;
                currentThread = null;
                // Notify waiting threads that the lock is released
                notify();
            }
        }
    }
}