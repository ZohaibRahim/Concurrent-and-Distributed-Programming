package Q2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

class sharedFile {
    private static final int files = 5;
    private static final Semaphore semaphore = new Semaphore(files, true);

    public static void writeToFile(int fileID, String content) {
        try (FileWriter writer = new FileWriter("file_" + fileID + ".txt", true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void accessFiles(int threadId) {
        Random rand = new Random();
        while (true) {
            try {
                semaphore.acquire();
                int fileToWrite = threadId % files; // Assign each thread to a file

                int num1 = rand.nextInt(20000);
                int num2 = rand.nextInt(20000);

                int addition = num1 + num2;
                int multiplication = num1 * num2;
                int subtraction = num1 - num2;

                String result = String.format("Thread %d: %d + %d = %d, %d * %d = %d, %d - %d = %d",
                        threadId, num1, num2, addition, num1, num2, multiplication, num1, num2, subtraction);

                writeToFile(fileToWrite, result);

                Thread.sleep(100); // Sleep for a short time to simulate other operations

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}



