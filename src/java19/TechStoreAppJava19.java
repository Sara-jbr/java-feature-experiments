package java19;

/**
 * TechStore Java 19 Edition
 * Demonstrates Java 18 features in a mini product management app.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TechStoreAppJava19 {

    public static void main(String[] args) throws Exception {
        System.out.println("=====  TechStore v9.0 (Java 19 Preview) =====\n");

        StoreService store = new StoreService();
        store.addProduct(new Product("MacBook Pro", 2500.0, Category.LAPTOP));
        store.addProduct(new Product("Logitech Mouse", 45.0, Category.ACCESSORY));
        store.addProduct(new Product("Samsung Monitor", 320.0, Category.MONITOR));

        // Display products
        store.displayProducts();

        // Pattern Matching for Switch demo
        Object obj = new Product("Mechanical Keyboard", 120.0, Category.ACCESSORY);
        String cat = switch (obj) {
            case Product(String n, double price, Category c) -> c.name();
            default -> "UNKNOWN";
        };
        System.out.println("\nPattern matching switch category: " + cat);

        // Virtual Threads: process multiple product updates concurrently
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Callable<String>> tasks = store.getProducts().stream()
                .map(p -> (Callable<String>) () -> "Processed: " + p.name())
                .toList();

        List<Future<String>> results = executor.invokeAll(tasks);
        results.forEach(r -> {
            try {
                System.out.println(r.get());
            } catch (Exception ignored) {
            }
        });
        executor.shutdown();

        // Structured Concurrency: batch processing
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> f1 = scope.fork(() -> "Generate report");
            Future<String> f2 = scope.fork(() -> "Send notifications");

            scope.join();
            scope.throwIfFailed();

            System.out.println("\nStructured Concurrency results:");
            System.out.println(f1.resultNow());
            System.out.println(f2.resultNow());
        }

        System.out.println("\n TechStore v9.0 running with Java 19 preview features!");
    }
}

// =======================================================
// 💡 Product record
// =======================================================
record Product(String name, double price, Category category) {
}

// =======================================================
// 💡 Category Enum
// =======================================================
enum Category {
    LAPTOP, ACCESSORY, MONITOR
}

// =======================================================
//  StoreService — product operations
// =======================================================
class StoreService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
        System.out.println("Added: " + p.name());
    }

    public void displayProducts() {
        System.out.println("\n Product List:");
        products.forEach(System.out::println);
    }

    public List<Product> getProducts() {
        return products;
    }
}

