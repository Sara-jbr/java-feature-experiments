package java13;

import java.nio.file.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TechStore Java 13 Edition
 * Demonstrates real-world usage of Java 13 features in a mini product management app.
 */
public class TechStoreAppJava13 {

    //  Inner Product class
    static class Product {
        String name;
        double price;
        int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ") - Stock: " + stock;
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println(" Welcome to TechStore (Java 13 Edition)");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Sample Products
        // ============================================================
        var products = List.of(
                new Product("MacBook Pro", 2500.0, 5),
                new Product("Dell XPS", 2200.0, 3),
                new Product("Keyboard", 100.0, 15),
                new Product("Mouse", 40.0, 25),
                new Product("Monitor", 300.0, 8)
        );

        // ============================================================
        // 2️⃣ Switch Expressions for Product Category
        // ============================================================
        System.out.println("\n Product Categories:");
        for (var p : products) {
            var category = switch (p.name) {
                case "MacBook Pro", "Dell XPS" -> "Laptop";
                case "Keyboard", "Mouse" -> "Accessory";
                case "Monitor" -> "Display";
                default -> "Other";
            };
            System.out.println(" " + p.name + " -> " + category);
        }

        // ============================================================
        // 3️⃣ Text Blocks for Product Catalog
        // ============================================================
        var catalog = """
                 Product Catalog
                ------------------
                %s
                ------------------
                """.formatted(
                products.stream().map(Product::toString).collect(Collectors.joining("\n"))
        );
        System.out.println("\n" + catalog);

        // ============================================================
        // 4️⃣ Compact Number Formatting (Stock)
        // ============================================================
        var formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println(" Stock Levels:");
        products.forEach(p -> System.out.println(p.name + ": " + formatter.format(p.stock)));

        // ============================================================
        // 5️⃣ String.transform() for Uppercase Names
        // ============================================================
        System.out.println("\n Uppercase Product Names:");
        products.stream()
                .map(p -> p.name.transform(String::toUpperCase))
                .forEach(System.out::println);

        // ============================================================
        // 6️⃣ Stream API + Collectors
        // ============================================================
        var distinctProducts = products.stream()
                .map(p -> p.name)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\n Distinct product names: " + distinctProducts);

        // ============================================================
        // 7️⃣ Teeing-like Summary using Java 13 constructs
        // ============================================================
        var summary = Map.of(
                "Total Value", products.stream().mapToDouble(p -> p.price * p.stock).sum(),
                "Average Price", products.stream().mapToDouble(p -> p.price).average().orElse(0)
        );
        System.out.println("\n Inventory Summary: " + summary);

        // ============================================================
        // 8️⃣ File Handling (Java 13 FileSystem improvements)
        // ============================================================
        var tempFile = Files.createTempFile("techstore-java13-", ".txt");
        var reportContent = products.stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
        Files.writeString(tempFile, reportContent);
        System.out.println("\n Product report written to temp file: " + tempFile);
        System.out.println("File content preview:\n" + Files.readString(tempFile));
        Files.deleteIfExists(tempFile);

        // ============================================================
        // 9️⃣ var in loops and try-with-resources
        // ============================================================
        System.out.println("\n Loop with var:");
        for (var p : products) {
            System.out.println("Product: " + p.name);
        }

        try (var scanner = new Scanner(System.in)) {
            System.out.println("\n Scanner ready (auto-close using var). Enter something to test: (press Enter to skip)");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(" Scanner closed.");
        }

        System.out.println("\n TechStore Java 13 demo complete!");
    }
}
