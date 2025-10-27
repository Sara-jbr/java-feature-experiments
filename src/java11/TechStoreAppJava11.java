package java11;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.net.http.*;
import java.net.URI;
import java.util.stream.Collectors;

/**
 * TechStore Java 11 Demo
 * A small console app demonstrating all major Java 11 features
 * through a real-world style product management scenario.
 */
public class TechStoreAppJava11 {

    // Product model (nested class)
    static class Product {
        private final String name;
        private final double price;
        private final String category;

        Product(String name, double price, String category) {
            this.name = name.strip(); // uses new String method
            this.price = price;
            this.category = category.strip();
        }

        @Override
        public String toString() {
            return String.format("%s (%.2f USD) - %s", name, price, category);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(" Welcome to TechStore (Java 11 Edition)!");
        System.out.println("=".repeat(50)); // uses String.repeat()

        // 1️⃣ Create a product list using immutable List.of()
        var products = List.of(
                new Product("Laptop ", 999.99, " Electronics "),
                new Product(" Mouse", 25.50, "Accessories "),
                new Product("Keyboard ", 45.00, " Accessories"),
                new Product("Monitor", 199.99, " Electronics "),
                new Product("Notebook", 5.99, " Stationery ")
        );

        // 2️⃣ Use var in loops and lambdas
        products.forEach((var p) -> System.out.println(" " + p));

        System.out.println("\n Generating Product Report...");
        System.out.println("-".repeat(50));

        // 3️⃣ Filter and collect using Stream enhancements
        var electronics = products.stream()
                .takeWhile(p -> !p.category.equalsIgnoreCase("Stationery"))
                .collect(Collectors.toUnmodifiableList());

        electronics.forEach(p -> System.out.println("🔌 Electronic: " + p));

        // 4️⃣ Optional.isEmpty()
        Optional<Product> expensiveProduct = products.stream()
                .filter(p -> p.price > 1000)
                .findFirst();
        if (expensiveProduct.isEmpty()) {
            System.out.println("\n No products above $1000 found!");
        }

        // 5️⃣ Write product list to a file (Files.writeString)
        Path reportFile = Paths.get("techstore_report.txt");
        var reportText = products.stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
        Files.writeString(reportFile, reportText);

        System.out.println("\n Report saved to: " + reportFile.toAbsolutePath());
        System.out.println(" File content preview:");
        System.out.println(Files.readString(reportFile));

        // 6️⃣ HTTP Client – simulate API request to check stock
        System.out.println("\n Checking product stock via mock API...");
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(" API Response (status): " + response.statusCode());
        System.out.println("Body preview: " + response.body().substring(0, 80) + "...");

        // 7️⃣ New String methods
        var userInput = "   Java11  ".strip();
        if (!userInput.isBlank()) {
            System.out.println("\n🧍‍♀️ User input accepted: " + userInput);
        }

        // 8️⃣ Nest-based access example
        System.out.println("\n Testing Nest-based Access:");
        new TechStoreAppJava11().testNestAccess();

        // 9️⃣ toArray(IntFunction)
        var productNames = products.stream().map(p -> p.name).toArray(String[]::new);
        System.out.println("\n Product names: " + Arrays.toString(productNames));

        System.out.println("\n TechStore Java 11 demo complete!");
    }

    // Nested class example for nest-based access
    private String storeSecret = "Top Secret: Java 11 improves nested access!";

    class InnerInspector {
        void revealSecret() {
            System.out.println(" InnerInspector can read: " + storeSecret);
        }
    }

    void testNestAccess() {
        new InnerInspector().revealSecret();
    }
}
