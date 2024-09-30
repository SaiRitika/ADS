import java.util.Scanner;

public class Quick {

    /* Function that considers the last element as pivot,
    places the pivot at its correct position, and places
    smaller elements to the left of pivot and greater
    elements to the right of pivot. */
    int partition(int a[], int start, int end) {
        int pivot = a[end]; // pivot element
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            // If current element is smaller than the pivot
            if (a[j] < pivot) {
                i++; // increment index of smaller element
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i + 1];
        a[i + 1] = a[end];
        a[end] = t;
        return (i + 1);
    }

    /* Function to implement quick sort */
    void quick(int a[], int start, int end) {
        if (start < end) {
            int p = partition(a, start, end); // p is partitioning index
            quick(a, start, p - 1);  // Recursively sort elements before partition
            quick(a, p + 1, end);  // Recursively sort elements after partition
        }
    }

    /* Function to print an array */
    void printArr(int a[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a Scanner object for dynamic input
        Scanner sc = new Scanner(System.in);

        // Ask the user for the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        // Create an array based on user input
        int[] a = new int[n];

        // Ask the user to input the array elements
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Display the array before sorting
        System.out.println("\nBefore sorting, array elements are - ");
        Quick q1 = new Quick();
        q1.printArr(a, n);

        // Perform quick sort
        q1.quick(a, 0, n - 1);

        // Display the array after sorting
        System.out.println("\nAfter sorting, array elements are - ");
        q1.printArr(a, n);

        // Close the scanner
        sc.close();
    }
}
