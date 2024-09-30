import java.util.Scanner;

public class BinarySearchTree {

    // Represent a node of binary tree
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            // Assign data to the new node, set left and right children to null
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Represent the root of binary tree
    public Node root;
    public static boolean flag = false;

    public BinarySearchTree() {
        root = null;
    }

    // insert() will add a new node to the binary search tree
    public void insert(int data) {
        Node newNode = new Node(data);

        // Check if the tree is empty
        if (root == null) {
            root = newNode;
        } else {
            Node current = root, parent = null;

            while (true) {
                parent = current;
                if (data < current.data) { // Left subtree
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else { // Right subtree
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    // minNode() will find the minimum node
    public Node minNode(Node root) {
        if (root.left != null) {
            return minNode(root.left);
        } else {
            return root;
        }
    }

    // deleteNode() will delete a node from the binary search tree
    public Node deleteNode(Node node, int value) {
        if (node == null) {
            return null;
        } else {
            if (value < node.data) {
                node.left = deleteNode(node.left, value);
            } else if (value > node.data) {
                node.right = deleteNode(node.right, value);
            } else { // Found the node to delete
                if (node.left == null && node.right == null) {
                    node = null;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else { // Node with two children
                    Node temp = minNode(node.right);
                    node.data = temp.data;
                    node.right = deleteNode(node.right, temp.data);
                }
            }
            return node;
        }
    }

    // inorderTraversal() will perform inorder traversal on binary search tree
    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // searchNode() will search for the particular node in the binary tree
    public void searchNode(Node node, int value) {
        if (node == null) {
            System.out.println("Tree is empty");
        } else if (node.data == value) {
            flag = true;
        } else if (value < node.data) {
            searchNode(node.left, value);
        } else {
            searchNode(node.right, value);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree bt = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nBinary Search Tree Operations:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Search for a node");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Insert a node
                    System.out.print("Enter value to insert: ");
                    int insertValue = sc.nextInt();
                    bt.insert(insertValue);
                    System.out.println(insertValue + " has been inserted.");
                    break;

                case 2: // Delete a node
                    System.out.print("Enter value to delete: ");
                    int deleteValue = sc.nextInt();
                    bt.deleteNode(bt.root, deleteValue);
                    System.out.println(deleteValue + " has been deleted.");
                    break;

                case 3: // Inorder Traversal
                    System.out.println("Inorder Traversal:");
                    bt.inorderTraversal(bt.root);
                    System.out.println(); // New line after traversal
                    break;

                case 4: // Search for a node
                    System.out.print("Enter value to search: ");
                    int searchValue = sc.nextInt();
                    bt.flag = false; // Reset flag before searching
                    bt.searchNode(bt.root, searchValue);
                    if (bt.flag) {
                        System.out.println(searchValue + " is present in the binary tree.");
                    } else {
                        System.out.println(searchValue + " is not present in the binary tree.");
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
