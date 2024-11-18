import java.util.Scanner;

public class GeometricAlgorithms {

    // Method to check if a point is inside a polygon (using the ray-casting algorithm)
    public static boolean isPointInPolygon(double[][] polygon, double[] point) {
        int n = polygon.length;
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon[i][0], yi = polygon[i][1];
            double xj = polygon[j][0], yj = polygon[j][1];

            if ((yi > point[1]) != (yj > point[1]) &&
                (point[0] < (xj - xi) * (point[1] - yi) / (yj - yi) + xi)) {
                inside = !inside;
            }
        }
        return inside;
    }

    // Function to find the distance from a point (x, y) to a line defined by two points (x1, y1) and (x2, y2)
    public static double distanceFromPointToLine(double x1, double y1, double x2, double y2, double x, double y) {
        // Calculate coefficients A, B, and C for the line equation Ax + By + C = 0
        double A = y2 - y1;
        double B = x1 - x2;
        double C = -(A * x1 + B * y1);

        // Calculate the perpendicular distance from (x, y) to the line
        return Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Geometric Operations ---");
            System.out.println("1. Check if a point is inside a polygon");
            System.out.println("2. Calculate distances from points to a line");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check if a point is inside a polygon
                    System.out.print("Enter the number of vertices of the polygon: ");
                    int n = scanner.nextInt();
                    double[][] polygon = new double[n][2];

                    System.out.println("Enter the vertices of the polygon (x y format):");
                    for (int i = 0; i < n; i++) {
                        polygon[i][0] = scanner.nextDouble();
                        polygon[i][1] = scanner.nextDouble();
                    }

                    System.out.print("Enter the point to check (x y format): ");
                    double[] point = new double[2];
                    point[0] = scanner.nextDouble();
                    point[1] = scanner.nextDouble();

                    boolean inside = isPointInPolygon(polygon, point);
                    if (inside) {
                        System.out.println("The point is inside the polygon.");
                    } else {
                        System.out.println("The point is outside the polygon.");
                    }
                    break;

                case 2:
                    // Calculate distances from points to a line
                    System.out.print("Enter x1, y1 for the first point on the line: ");
                    double x1 = scanner.nextDouble();
                    double y1 = scanner.nextDouble();

                    System.out.print("Enter x2, y2 for the second point on the line: ");
                    double x2 = scanner.nextDouble();
                    double y2 = scanner.nextDouble();

                    System.out.print("Enter the number of points: ");
                    int numPoints = scanner.nextInt();

                    double totalDistance = 0.0; // Variable to accumulate the sum of distances

                    for (int i = 1; i <= numPoints; i++) {
                        System.out.print("Enter x" + i + ", y" + i + " for point " + i + ": ");
                        double x = scanner.nextDouble();
                        double y = scanner.nextDouble();

                        double distance = distanceFromPointToLine(x1, y1, x2, y2, x, y);
                        System.out.println("The distance from point " + i + " to the line is: " + distance);

                        totalDistance += distance;
                    }

                    // Calculate and print the mean distance
                    double meanDistance = totalDistance / numPoints;
                    System.out.println("The mean distance from the points to the line is: " + meanDistance);
                    break;

                case 3:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
