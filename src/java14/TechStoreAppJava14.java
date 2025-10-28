package java14;


import java.util.*;
import java.util.stream.Collectors;

/**
 * TechStore Java 14 Edition
 * Demonstrates Java 14 features in a real-world mini product management app.
 */
public class TechStoreAppJava14 {

    //  Use Record for Product (Java 14)
    record Product(String name, double price, int stock) {
    }

    public static void main(String[] args) {

        System.out.println(" Welcome to TechStore (Java 14 Edition)");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Create Sample Products
        // ============================================================
        var products = List.of(
                new Product("MacBook Pro", 2500.0, 5),
                new Product("Dell XPS", 2200.0, 3),
                new Product("Keyboard", 100.0, 15),
                new Product("Mouse", 40.0, 25)
        );

        // ============================================================
        // 2️⃣ Switch Expression for Product Category
        // ============================================================
        System.out.println("\n Product Categories:");
        for (var p : products) {
            var category = switch (p.name()) {
                case "MacBook Pro", "Dell XPS" -> "Laptop";
                case "Keyboard", "Mouse" -> "Accessory";
                default -> "Other";
            };
            System.out.println(" " + p.name() + " -> " + category);
        }

        // ============================================================
        // 3️⃣ Text Block for Product Catalog
        // ============================================================
        var catalog = """
                 Product Catalog
                ------------------
                %s
                ------------------
                """.formatted(
                products.stream()
                        .map(Product::toString)
                        .collect(Collectors.joining("\n"))
        );
        System.out.println("\n" + catalog);

        // ============================================================
        // 4️⃣ Stream API & Total Stock Calculation
        // ============================================================
        var totalStock = products.stream().mapToInt(Product::stock).sum();
        System.out.println(" Total Stock: " + totalStock);

        // ============================================================
        // 5️⃣ Pattern Matching for instanceof
        // ============================================================
        Object obj = "Java 14 TechStore!";
        if (obj instanceof String s) {
            System.out.println(" Pattern matched string: " + s.toUpperCase());
        }

        // ============================================================
        // 6️⃣ Switch Expression with multiple statements and yield
        // ============================================================
        var day = "SUNDAY";
        var dayType = switch (day) {
            case "SATURDAY", "SUNDAY" -> {
                System.out.println("Processing weekend...");
                yield "Weekend";
            }
            default -> {
                System.out.println("Processing weekday...");
                yield "Weekday";
            }
        };
        System.out.println("Day Type: " + dayType);

        System.out.println("\n TechStore Java 14 demo complete!");
    }
}

