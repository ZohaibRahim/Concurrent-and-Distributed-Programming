package Q1;

import java.util.Random;
public class ComplexThread extends Thread {
    private int [] array = new int [1000000000];
    public void run(){
        Random rand = new Random();
        while (true){
            int i = rand.nextInt(array.length);
            array[i]++;
        }
    }

    public static void main(String[] args) {
        int noOfComplexThreads=0;
                try {
                    while (true){
                        new ComplexThread().start();
                        noOfComplexThreads++;
                    }
                }
                catch (OutOfMemoryError e){
                    System.out.println("Out of memory after creating "+ noOfComplexThreads + " complex threads.");
                }
    }
}
