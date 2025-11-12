package java22;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import static java.lang.StringTemplate.STR;

/**
 * TechStore App using Java 22 features
 * Enhanced inventory management and promotions using Java 22 features.
 */
public class TechStoreAppJava22 {

    public static void main(String[] args) throws Exception {
        System.out.println("===== TechStore App (Java 22) =====\n");

        List<Product> inventory = List.of(
                new Product("MacBook Pro", 2500, "Laptop"),
                new Product("Dell XPS", 2000, "Laptop"),
                new Product("Logitech Mouse", 45, "Accessory"),
                new Product("Samsung Monitor", 320, "Monitor")
        );

        // =========================================================
        // Record Pattern Improvements: match Orders
        // =========================================================
        record Order(Product product, int quantity) {}
        Object obj = new Order(inventory.get(0), 5);

        if (obj instanceof Order(Product(String name, double price, String category), int qty)) {
            System.out.println("Order: " + name + " | Qty: " + qty + " | Category: " + category);
        }

        // =========================================================
        // Pattern Matching Refinements: categorize price
        // =========================================================
        double price = inventory.get(1).price();
        String priceCategory = switch ((int) price) {
            case int p && p > 2000 -> "Expensive";
            case int p -> "Affordable";
            default -> "Unknown";
        };
        System.out.println("Price category for " + inventory.get(1).name() + ": " + priceCategory);

        // =========================================================
        // Virtual Threads: simulate multiple concurrent tasks
        // =========================================================
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Callable<String>> tasks = List.of(
                () -> "Check stock",
                () -> "Apply discounts",
                () -> "Generate invoice"
        );
        for (Future<String> f : executor.invokeAll(tasks)) System.out.println(f.get());
        executor.shutdown();

        // =========================================================
        // Scoped Values Enhancements: multiple scoped variables
        // =========================================================
        ScopedValue<Integer> discount = ScopedValue.newInstance(0);
        ScopedValue<String> promo = ScopedValue.newInstance("None");
        try (ScopedValue.Scope scope = ScopedValue.where(discount, 15, promo, "Autumn Sale")) {
            System.out.println("Discount: " + discount.get() + "% | Promotion: " + promo.get());
        }

        // =========================================================
        // String Templates Enhanced
        // =========================================================
        Product prod = inventory.get(0);
        String template = STR."Product: \{prod.name()} | Price: $\{prod.price()} | Stock: 20 | Promo: Autumn Sale";
        System.out.println("\n" + template);

        // =========================================================
        // Stream Gatherers: join product names
        // =========================================================
        String namesJoined = inventory.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().map(Product::name).collect(Collectors.joining(", "))));
        System.out.println("All product names: " + namesJoined);

        // =========================================================
        // Sequenced Collections Enhanced
        // =========================================================
        Map<String, Double> prices = new LinkedHashMap<>();
        inventory.forEach(p -> prices.put(p.name(), p.price()));
        System.out.println("\n Product Prices (sequenced):");
        prices.forEach((k, v) -> System.out.println(k + " -> $" + v));
    }

    record Product(String name, double price, String category) {}
}
