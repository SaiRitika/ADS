import java.util.Scanner;

class Merge {

    /* Function to merge the subarrays of a[] */
    void merge(int a[], int beg, int mid, int end) {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        /* temporary Arrays */
        int LeftArray[] = new int[n1];
        int RightArray[] = new int[n2];

        /* copy data to temp arrays */
        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];

        i = 0; /* initial index of first sub-array */
        j = 0; /* initial index of second sub-array */
        k = beg; /* initial index of merged sub-array */

        while (i < n1 && j < n2) {
            if (LeftArray[i] <= RightArray[j]) {
                a[k] = LeftArray[i];
                i++;
            } else {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    void mergeSort(int a[], int beg, int end) {
        if (beg < end) {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merge(a, beg, mid, end);
        }
    }

    /* Function to print the array */
    void printArray(int a[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Ask the user for the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        // Create an array with the specified size
        int a[] = new int[n];

        // Accept elements from the user
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Create an instance of the Merge class
        Merge m1 = new Merge();

        // Display the array before sorting
        System.out.println("\nBefore sorting, array elements are:");
        m1.printArray(a, n);

        // Perform merge sort
        m1.mergeSort(a, 0, n - 1);

        // Display the array after sorting
        System.out.println("\nAfter sorting, array elements are:");
        m1.printArray(a, n);
        System.out.println();
        
        sc.close();
    }
}
