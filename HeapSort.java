import java.util.Scanner;

class HeapSort {

    /* function to heapify a subtree. Here 'i' is the index of root node in array a[], and 'n' is the size of heap. */
    static void heapify(int a[], int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child
        int right = 2 * i + 2; // right child

        // If left child is larger than root
        if (left < n && a[left] > a[largest])
            largest = left;

        // If right child is larger than root
        if (right < n && a[right] > a[largest])
            largest = right;

        // If root is not largest
        if (largest != i) {
            // swap a[i] with a[largest]
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            // Recursively heapify the affected subtree
            heapify(a, n, largest);
        }
    }

    /* Function to implement the heap sort */
    static void heapSort(int a[], int n) {
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root element to end
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            // Call heapify on the reduced heap
            heapify(a, i, 0);
        }
    }

    /* Function to print the array elements */
    static void printArr(int a[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        // Create a scanner object for taking dynamic inputs
        Scanner sc = new Scanner(System.in);

        // Ask the user for the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        // Create an array with the given size
        int a[] = new int[n];

        // Accept elements from the user
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Display the array before sorting
        System.out.print("Before sorting, array elements are: \n");
        printArr(a, n);

        // Perform heap sort
        heapSort(a, n);

        // Display the array after sorting
        System.out.print("\nAfter sorting, array elements are: \n");
        printArr(a, n);

        sc.close();  // Close the scanner
    }
}
