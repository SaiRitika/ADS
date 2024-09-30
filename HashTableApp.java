import java.util.Scanner;

class Data<K, V> {
    K key;
    V value;

    Data(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable<K, V> {
    private static final int TABLE_SIZE = 10;
    private Data<K, V>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new Data[TABLE_SIZE];
    }

    private int hashFunction(K key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    public void insert(K key, V value) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % TABLE_SIZE;
            if (index == startIndex) {
                System.out.println("Hash table is full. Cannot insert new entry.");
                return;
            }
        }
        table[index] = new Data<>(key, value);
        System.out.println("Entry inserted/updated successfully.");
    }

    public void search(K key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                System.out.println("Value for key '" + key + "' is: " + table[index].value);
                return;
            }
            index = (index + 1) % TABLE_SIZE;
            if (index == startIndex) {
                System.out.println("Key not found.");
                return;
            }
        }
        System.out.println("Key not found.");
    }

    public void delete(K key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index] = null;
                System.out.println("Entry deleted successfully.");
                return;
            }
            index = (index + 1) % TABLE_SIZE;
            if (index == startIndex) {
                System.out.println("Key not found.");
                return;
            }
        }
        System.out.println("Key not found.");
    }

    public void display() {
        boolean isEmpty = true;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                System.out.println("Key: " + table[i].key + " -> Value: " + table[i].value);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No entries to display.");
        }
    }
}

public class HashTableApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable<String, String> hashTable = new HashTable<>();

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Insert key-value pair");
            System.out.println("2. Find value by key");
            System.out.println("3. Delete key-value pair");
            System.out.println("4. Display all key-value pairs");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the key: ");
                    String keyToInsert = scanner.nextLine();
                    System.out.print("Enter the value: ");
                    String valueToInsert = scanner.nextLine();
                    hashTable.insert(keyToInsert, valueToInsert);
                    break;
                case 2:
                    System.out.print("Enter the key whose value you want: ");
                    String keyToSearch = scanner.nextLine();
                    hashTable.search(keyToSearch);
                    break;
                case 3:
                    System.out.print("Enter the key to be deleted: ");
                    String keyToDelete = scanner.nextLine();
                    hashTable.delete(keyToDelete);
                    break;
                case 4:
                    hashTable.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown choice. Please enter a valid option.");
            }
        }
    }
}