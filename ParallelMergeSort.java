package Project_CPSC222;

import java.util.Comparator;
import java.util.concurrent.*;

//Implements parallel merge sort algorithm using ForkJoinPool for parallel execution.
public class ParallelMergeSort {
    private static final int sequentialThreshold = 1000; // Threshold for switching to sequential merge sort
    private final Comparator<Integer> comparator;
    private final ForkJoinPool forkJoinPool; // Using ForkJoinPool for parallel execution

    //Initializes ParallelMergeSort object with given comparator.
    public ParallelMergeSort(Comparator<Integer> comparator) {
        this.comparator = comparator; //to determine the order of elements
        int parallelism = Runtime.getRuntime().availableProcessors();
        forkJoinPool = new ForkJoinPool(parallelism); // Creating ForkJoinPool with available processors
    }

    //Sorts the given array in parallel using merge sort algorithm.
    public void sort(int[] arr) {
        int[] result = new int[arr.length];
        ParallelMergeSortTask task = new ParallelMergeSortTask(arr, result, 0, arr.length - 1);
        forkJoinPool.invoke(task); // Initiating parallel merge sort task
    }

    //RecursiveAction implementation for parallel merge sort.
    private class ParallelMergeSortTask extends RecursiveAction {
        private final int[] arr; //array to be sorted.
        private final int[] result; //array for merging.
        private final int low;//lower index of the array.
        private final int high; //higher index of the array.

        //Constructs ParallelMergeSortTask with given parameters.
        ParallelMergeSortTask(int[] arr, int[] result, int low, int high) {
            this.arr = arr;
            this.result = result;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low < sequentialThreshold) { // Checking if the array size is below the sequential threshold
                // Use sequential merge sort for small sub-arrays
                sequentialMergeSort(arr, result, low, high);
            } else {
                int mid = low + (high - low) / 2;
                ParallelMergeSortTask leftTask = new ParallelMergeSortTask(arr, result, low, mid);
                ParallelMergeSortTask rightTask = new ParallelMergeSortTask(arr, result, mid + 1, high);
                invokeAll(leftTask, rightTask); // Executing left and right tasks in parallel
                merge(arr, result, low, mid, high);
            }
        }
    }

    //Performs sequential merge sort on the array.
    private void sequentialMergeSort(int[] arr, int[] result, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            sequentialMergeSort(arr, result, low, mid);
            sequentialMergeSort(arr, result, mid + 1, high);
            merge(arr, result, low, mid, high);
        }
    }

    //Merges two sorted sub-arrays into one sorted array.
    private void merge(int[] arr, int[] result, int low, int mid, int high) {
        // Initialize pointers for the left and right sub-arrays
        int left = low;
        int right = mid + 1;
        // Initialize index for the merged array
        int index = low;

        // Merge elements from both sub-arrays into the result array
        while (left <= mid && right <= high) {
            // Compare elements from the left and right sub-arrays
            if (comparator.compare(arr[left], arr[right]) <= 0) {
                // If element from left sub-array is smaller or equal, add it to the result array
                result[index++] = arr[left++];
            } else {
                // If element from right sub-array is smaller, add it to the result array
                result[index++] = arr[right++];
            }
        }

        // Copy any remaining elements from the left sub-array to the result array
        while (left <= mid) {
            result[index++] = arr[left++];
        }

        // Copy any remaining elements from the right sub-array to the result array
        while (right <= high) {
            result[index++] = arr[right++];
        }

        // Copy the merged elements from the result array back to the original array
        for (index = low; index <= high; index++) {
            arr[index] = result[index];
        }
    }


    //Prints the elements of the array.
    public void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
