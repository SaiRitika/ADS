import java.util.Scanner;

class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    // Initializing front as static with unity
    private static final int FRONT = 1;

    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;  // Sentinel value for easy comparison
    }

    // Method 1: Return the position of the parent of the node at pos
    private int parent(int pos) {
        return pos / 2;
    }

    // Method 2: Return the position of the left child of the node at pos
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Method 3: Return the position of the right child of the node at pos
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Method 4: Return true if the node at pos is a leaf node
    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    // Method 5: Swap two nodes of the heap
    private void swap(int fpos, int spos) {
        int tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Method 6: To heapify the node at pos (for MaxHeap)
    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;

        // Find the largest value among current node and its children
        if (left <= size && Heap[left] > Heap[largest])
            largest = left;
        if (right <= size && Heap[right] > Heap[largest])
            largest = right;

        // If the current node is smaller than its largest child, swap and recurse
        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    // Method 7: Insert a node into the heap
    public void insert(int element) {
        if (size >= maxsize) {
            System.out.println("Heap is full. Cannot insert.");
            return;
        }

        Heap[++size] = element;
        int current = size;

        // Fix the max heap property if it is violated
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Method 8: Print the heap content
    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            String leftChild = (2 * i <= size) ? String.valueOf(Heap[2 * i]) : "null";
            String rightChild = (2 * i + 1 <= size) ? String.valueOf(Heap[2 * i + 1]) : "null";
            System.out.println(
                    " PARENT : " + Heap[i] + " LEFT CHILD : " + leftChild + " RIGHT CHILD : " + rightChild);
        }
    }

    // Method 9: Remove and return the maximum element from the heap
    public int remove() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        maxHeapify(FRONT);
        return popped;
    }

    // Method 10: Search for an element in the heap
    public void search(int element) {
        boolean found = false;
        for (int i = 1; i <= size; i++) {
            if (Heap[i] == element) {
                System.out.println(element + " is present in the heap.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(element + " is not present in the heap.");
        }
    }

    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the maximum size of the Max Heap: ");
        int maxsize = scan.nextInt();
        MaxHeap maxHeap = new MaxHeap(maxsize);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Delete Max");
            System.out.println("3. Search");
            System.out.println("4. Print");
            System.out.println("5. Exit");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter value to insert: ");
                    int value = scan.nextInt();
                    maxHeap.insert(value);
                    break;
                case 2:
                    int deleted = maxHeap.remove();
                    if (deleted != -1)
                        System.out.println("Deleted max value: " + deleted);
                    break;
                case 3:
                    System.out.println("Enter value to search: ");
                    int searchValue = scan.nextInt();
                    maxHeap.search(searchValue);
                    break;
                case 4:
                    maxHeap.print();
                    break;
                case 5:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
