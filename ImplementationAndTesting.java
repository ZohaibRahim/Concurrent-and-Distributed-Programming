package Project_CPSC222;

import java.util.Comparator;

public class ImplementationAndTesting {
    public static void main(String[] args) {
        // TEST 1 - Normal Data including duplicates
        int[] arr1 = {100, 12, 11, 13, 5, 6, 7, 1, 13, 54, 21, 1, 4, 2, 6, 4, 3, 8, 5, 3, 12, 32, 14, 54, 32, 11, 12, 21, 21, 12, 53, 0};
        Comparator<Integer> comparator1 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort1 = new ParallelMergeSort(comparator1);
        System.out.println("Test 1 - Normal Data including duplicates");
        mergeSort1.sort(arr1); // Sort the input array
        mergeSort1.printArray(arr1);
        System.out.println();

        // TEST 2 - Reverse Order
        int[] arr2 = {5, 2, 4, 6, 1, 3, 2, 6};
        Comparator<Integer> comparator2 = Comparator.reverseOrder(); // Comparator for reverse ordering of integers
        ParallelMergeSort mergeSort2 = new ParallelMergeSort(comparator2);
        System.out.println("Test 2 - Reverse Order");
        mergeSort2.sort(arr2); // Sort the input array
        mergeSort2.printArray(arr2);
        System.out.println();

        // TEST 3 - Including Negative Numbers
        int[] arr3 = {10, 9, 8, -7, 6, 5, 4, -3, 2, 1};
        Comparator<Integer> comparator3 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort3 = new ParallelMergeSort(comparator3);
        System.out.println("Test 3 - Including Negative Numbers");
        mergeSort3.sort(arr3); // Sort the input array
        mergeSort3.printArray(arr3);
        System.out.println();

        // TEST 4 - Empty Set
        int[] arr4 = {};
        Comparator<Integer> comparator4 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort4 = new ParallelMergeSort(comparator4);
        System.out.println("Test 4 - Empty Set");
        mergeSort4.sort(arr4); // Sort the input array
        mergeSort4.printArray(arr4);
        System.out.println();

        // TEST 5 - Single data
        int[] arr5 = {1};
        Comparator<Integer> comparator5 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort5 = new ParallelMergeSort(comparator5);
        System.out.println("Test 5 - Single data");
        mergeSort5.sort(arr5); // Sort the input array
        mergeSort5.printArray(arr5);
        System.out.println();


        //TIMING Comparison
        long startTime, endTime;

        // TEST 1
        int[] arr6 = new int[1_000_000];
        for (int i = 0; i < arr6.length; i++) {
            arr6[i] = (int) (Math.random() * 1_000_000); // Generate random integers
        }
        Comparator<Integer> comparator6 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort6 = new ParallelMergeSort(comparator6);
        System.out.println("TIMINGS:");
        System.out.println("Test 1 - Size: 1,000,000");

        startTime = System.currentTimeMillis();
        SequentialMergeSort.sequentialMergeSort(arr6); // Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Sequential Sorting: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        mergeSort6.sort(arr6);// Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Parallel Sorting: " + (endTime - startTime) + " ms");

        // mergeSort6.printArray(arr6); (not recommended for large arrays)
        System.out.println();

        // TEST 2
        int[] arr7 = new int[10_000_000];
        for (int i = 0; i < arr7.length; i++) {
            arr7[i] = (int) (Math.random() * 10_000_000); // Generate random integers
        }
        Comparator<Integer> comparator7 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort7 = new ParallelMergeSort(comparator7);
        System.out.println("Test 2 - Size: 10,000,000");

        startTime = System.currentTimeMillis();
        SequentialMergeSort.sequentialMergeSort(arr7); // Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Sequential Sorting: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        mergeSort7.sort(arr7);// Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Parallel Sorting: " + (endTime - startTime) + " ms");
        System.out.println();


        // TEST 3
        int[] arr8 = new int[100_000_000];
        for (int i = 0; i < arr8.length; i++) {
            arr8[i] = (int) (Math.random() * 100_000_000); // Generate random integers
        }
        Comparator<Integer> comparator8 = Comparator.naturalOrder(); // Comparator for natural ordering of integers
        ParallelMergeSort mergeSort8 = new ParallelMergeSort(comparator8);
        System.out.println("Test 3 - Size: 100,000,000");
        startTime = System.currentTimeMillis();
        SequentialMergeSort.sequentialMergeSort(arr8); // Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Sequential Sorting: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        mergeSort8.sort(arr8);// Sort the input array
        endTime = System.currentTimeMillis();
        System.out.println("Sorting time of Parallel Sorting: " + (endTime - startTime) + " ms");
        System.out.println();
    }
}


