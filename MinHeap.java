import java.util.Scanner;

class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    // Initializing front as static with unity
    private static final int FRONT = 1;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE; // Sentinel value for comparison
    }

    // Return the position of the parent of the node at pos
    private int parent(int pos) {
        return pos / 2;
    }

    // Return the position of the left child of the node at pos
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Return the position of the right child of the node at pos
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Return true if the node at pos is a leaf node
    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    // Swap two nodes of the heap
    private void swap(int fpos, int spos) {
        int tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // To heapify the node at pos
    private void minHeapify(int pos) {
        if (isLeaf(pos)) {
            return;
        }

        int left = leftChild(pos);
        int right = rightChild(pos);
        int smallest = pos;

        if (left <= size && Heap[left] < Heap[smallest]) {
            smallest = left;
        }

        if (right <= size && Heap[right] < Heap[smallest]) {
            smallest = right;
        }

        if (smallest != pos) {
            swap(pos, smallest);
            minHeapify(smallest);
        }
    }

    // Insert a node into the heap
    public void insert(int element) {
        if (size >= maxsize) {
            System.out.println("Heap is full. Cannot insert.");
            return;
        }

        Heap[++size] = element;
        int current = size;

        // Fix the min heap property if it is violated
        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Print the contents of the heap
    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            String leftChild = (2 * i <= size) ? String.valueOf(Heap[2 * i]) : "null";
            String rightChild = (2 * i + 1 <= size) ? String.valueOf(Heap[2 * i + 1]) : "null";
            System.out.println(
                    "PARENT : " + Heap[i] + " LEFT CHILD : " + leftChild + " RIGHT CHILD : " + rightChild);
        }
    }

    // Remove and return the minimum element from the heap
    public int remove() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }

    // Search for an element in the heap
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
        System.out.println("Enter the maximum size of the Min Heap: ");
        int maxsize = scan.nextInt();
        MinHeap minHeap = new MinHeap(maxsize);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Delete Min");
            System.out.println("3. Search");
            System.out.println("4. Print");
            System.out.println("5. Exit");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter value to insert: ");
                    int value = scan.nextInt();
                    minHeap.insert(value);
                    break;
                case 2:
                    int deleted = minHeap.remove();
                    if (deleted != -1)
                        System.out.println("Deleted min value: " + deleted);
                    break;
                case 3:
                    System.out.println("Enter value to search: ");
                    int searchValue = scan.nextInt();
                    minHeap.search(searchValue);
                    break;
                case 4:
                    minHeap.print();
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
