package java12;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TechStore Java 12 Edition
 * Demonstrates real-world usage of Java 12 features in a product management app.
 */
public class TechStoreAppJava12 {

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

    public static void main(String[] args) {

        System.out.println(" Welcome to TechStore (Java 12 Edition)");
        System.out.println("=".repeat(60));

        var products = List.of(
                new Product("MacBook Pro", 2500.0, 5),
                new Product("Dell XPS", 2200.0, 3),
                new Product("Keyboard", 100.0, 15),
                new Product("Mouse", 40.0, 25)
        );

        // ============================================================
        // 1️⃣ Switch Expression for category determination
        // ============================================================
        for (var p : products) {
            var category = switch (p.name) {
                case "MacBook Pro", "Dell XPS" -> "Laptop";
                case "Keyboard", "Mouse" -> "Accessory";
                default -> "Other";
            };
            System.out.println(" " + p.name + " is categorized as: " + category);
        }

        // ============================================================
        // 2️⃣ Compact Number Formatting for stock
        // ============================================================
        var formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println("\n Stock Levels:");
        products.forEach(p -> System.out.println(p.name + ": " + formatter.format(p.stock)));

        // ============================================================
        // 3️⃣ String.transform() for formatting product names
        // ============================================================
        System.out.println("\n Formatted Product Names:");
        products.stream()
                .map(p -> p.name.transform(s -> s.toUpperCase(Locale.ROOT)))
                .forEach(System.out::println);

        // ============================================================
        // 4️⃣ Collectors.teeing() for total & average price
        // ============================================================
        var summary = products.stream()
                .collect(Collectors.teeing(
                        Collectors.summingDouble(p -> p.price),
                        Collectors.averagingDouble(p -> p.price),
                        (total, avg) -> Map.of("Total Value", total, "Average Price", avg)
                ));
        System.out.println("\n Price Summary: " + summary);

        // ============================================================
        // 5️⃣ String.indent() for pretty printing
        // ============================================================
        var report = new StringBuilder(" Product Report:\n");
        products.forEach(p -> report.append(p.toString()).append("\n"));
        System.out.println(report.toString().indent(4));

        // ============================================================
        // 6️⃣ JVM and GC improvements (conceptual)
        // ============================================================
        System.out.println("""
                    ️ JVM Enhancements:
                    - Shenandoah GC for low-latency
                    - Microbenchmarking framework
                    - Switch expressions preview
                """);

        System.out.println(" TechStore Java 12 demo complete!");
    }
}
