package java22;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.lang.foreign.ScopedValue;
import static java.lang.StringTemplate.STR;

/**
 * TechStore App using Java 21 features
 * Demonstrates inventory management, orders, and reports using Java 21 features.
 */
public class TechStoreAppJava21 {

    public static void main(String[] args) throws Exception {
        System.out.println("===== TechStore App (Java 21) =====\n");

        List<Product> inventory = List.of(
                new Product("MacBook Pro", 2500, "Laptop"),
                new Product("Dell XPS", 2000, "Laptop"),
                new Product("Logitech Mouse", 45, "Accessory"),
                new Product("Samsung Monitor", 320, "Monitor")
        );

        // =========================================================
        // Record Patterns: Filtering Laptops
        // =========================================================
        System.out.println("📦 Laptops in inventory:");
        for (Product p : inventory) {
            if (p instanceof Product(String name, double price, String category) && category.equals("Laptop")) {
                System.out.println(name + " - $" + price);
            }
        }

        // =========================================================
        // Virtual Threads: simulate order processing
        // =========================================================
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Callable<String>> tasks = List.of(
                () -> "Check inventory",
                () -> "Process payment",
                () -> "Generate invoice"
        );
        for (Future<String> f : executor.invokeAll(tasks)) System.out.println(f.get());
        executor.shutdown();

        // =========================================================
        // Scoped Values: temporary discount during promotion
        // =========================================================
        ScopedValue<Integer> discount = ScopedValue.newInstance(0);
        try (ScopedValue.Scope scope = ScopedValue.where(discount, 10)) {
            System.out.println("Temporary promotion discount: " + discount.get() + "%");
        }
        System.out.println("Outside promotion: " + discount.get() + "%");

        // =========================================================
        // String Templates: display product
        // =========================================================
        Product prod = inventory.get(0);
        String template = STR."Product: \{prod.name()} | Price: $\{prod.price()} | Category: \{prod.category()}";
        System.out.println("\n" + template);

        // =========================================================
        // Sequenced Collections: maintain insertion order
        // =========================================================
        Map<String, Double> productPrices = Map.of(
                "MacBook Pro", 2500.0,
                "Dell XPS", 2000.0,
                "Logitech Mouse", 45.0,
                "Samsung Monitor", 320.0
        );
        System.out.println("\n📊 Product Prices (in insertion order):");
        Map<String, Double> sequenced = Map.copyOf(productPrices);
        sequenced.forEach((k, v) -> System.out.println(k + " -> $" + v));
    }

    record Product(String name, double price, String category) {}
}
